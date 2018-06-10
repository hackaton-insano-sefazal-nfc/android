package br.com.danielsan.notafiscalcidada.features.main

import android.Manifest
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Rect
import android.os.Bundle
import android.support.v4.app.ShareCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.View
import br.com.danielsan.notafiscalcidada.Constants
import br.com.danielsan.notafiscalcidada.R
import br.com.danielsan.notafiscalcidada.base.AnalyticsActivity
import br.com.danielsan.notafiscalcidada.databinding.ActivityMainBinding
import br.com.danielsan.notafiscalcidada.domain.raffle.Raffle
import br.com.danielsan.notafiscalcidada.domain.user.UserResponse
import br.com.danielsan.notafiscalcidada.features.about.AboutActivity
import br.com.danielsan.notafiscalcidada.features.adopt.AdoptActivity
import br.com.danielsan.notafiscalcidada.features.authentication.AuthActivity
import br.com.danielsan.notafiscalcidada.features.invoices.InvoicesActivity
import br.com.danielsan.notafiscalcidada.features.qrcode.QrCodeActivity
import br.com.danielsan.notafiscalcidada.features.raffles.RafflesActivity
import br.com.danielsan.notafiscalcidada.features.report.list.ReportsActivity
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.android.AndroidInjection
import io.rapidpro.sdk.FcmClient
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnNeverAskAgain
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.OnShowRationale
import permissions.dispatcher.PermissionRequest
import permissions.dispatcher.RuntimePermissions
import javax.inject.Inject

@RuntimePermissions
class MainActivity : AnalyticsActivity(), MainContract {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    @Inject
    lateinit var presenter: MainPresenter
    private val ticketsSpace by lazy {
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6F, resources.displayMetrics).toInt()
    }
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }
    private val ticketsCirclesAdapter by lazy { TicketsCirclesAdapter() }
    private val syncDrawerStateRunnable = Runnable { drawerToggle.syncState() }
    private val drawerToggle by lazy { DrawerToggle(this, binding.drawer, binding.toolbar, binding.appBar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding.presenter = presenter
        FcmClient.requestFloatingPermissionsIfNeeded(this)
        setupView()
        presenter.attachView(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (drawerToggle.onOptionsItemSelected(item)) {
            true
        } else {
            when (item.itemId) {
                android.R.id.home -> true
                R.id.mn_invoices -> { presenter.onClickInvoices(); true }
                R.id.mn_raffles -> { presenter.onClickRaffles(); true }
                R.id.mn_about -> { presenter.onClickAbout(); true }
                R.id.mn_source -> { presenter.onClickOpenSource(); true }
                R.id.mn_push -> { presenter.onClickPush(); true }
                R.id.mn_exit -> {
                    logEvent("logout", mapOf())
                    presenter.onClickLogout()
                    true
                }
                R.id.mn_share -> { shareApp(); true }
                else -> super.onOptionsItemSelected(item)
            }
        }
    }

    override fun navigateToAuthenticate() {
        startActivity(AuthActivity.createIntent(this))
        finish()
    }

    override fun navigateToDenounce() {
        startActivity(ReportsActivity.createIntent(this))
    }

    override fun showUserInfo(userResponse: UserResponse) {
        ticketsCirclesAdapter.invoicesCount = userResponse.invoices
        binding.balance.text = Constants.CURRENCY_FORMAT.format(userResponse.credits)
        binding.tickets.text = getString(R.string.tickets_acquired, userResponse.numberOfTickets)
        val dateOfRealization = userResponse.nextRaffle?.dateOfRealization
        binding.raffleDate.text = if (dateOfRealization != null) {
            Constants.DATE_FORMAT.format(dateOfRealization)
        } else {
            "--/--/--"
        }
    }

    override fun navigateToYourTicket() {
        navigateToYourTicketInternalWithPermissionCheck()
    }

    override fun navigateToRanking() {
        // FIXME: This feature isn't complemented
//        startActivity(RankingActivity.createIntent(this))
        showMessage("Estará disponível em breve!")
    }

    override fun navigateToDonation() {
        startActivity(AdoptActivity.createIntent(this))
    }

    override fun navigateToPush(cpf: String) {
        logEvent(FirebaseAnalytics.Event.VIEW_ITEM, mapOf(FirebaseAnalytics.Param.ITEM_ID to "ilha_push"))
        FcmClient.registerContact(cpf)
        FcmClient.startFcmClientChatActivity(this)
    }

    override fun navigateToAbout() {
        startActivity(AboutActivity.createIntent(this))
    }

    override fun navigateToOpenSource() {
        logEvent(FirebaseAnalytics.Event.VIEW_ITEM, mapOf(FirebaseAnalytics.Param.ITEM_ID to "open_source"))
        startActivity(Intent(this, OssLicensesMenuActivity::class.java))
    }

    override fun navigateToInvoices() {
        startActivity(InvoicesActivity.createIntent(this))
    }

    override fun navigateToRaffles() {
        startActivity(RafflesActivity.createIntent(this))
    }

    override fun showNextRaffle(raffle: Raffle) {
        AlertDialog.Builder(this)
                .setTitle(getString(R.string.title_dialog_raffle, raffle.code))
                .setMessage("${Constants.DATE_FORMAT.format(raffle.dateOfRealization)}: ${raffle.description}")
                .setPositiveButton(android.R.string.ok, null)
                .show()
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    fun navigateToYourTicketInternal() {
        startActivity(QrCodeActivity.createIntent(this))
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    fun showRationaleForCamera(request: PermissionRequest) {
        AlertDialog.Builder(this)
                .setMessage(R.string.permission_camera_rationale)
                .setPositiveButton(R.string.allow, { _, _ -> request.proceed() })
                .setNegativeButton(R.string.deny, { _, _ -> request.cancel() })
                .show()
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    fun showDeniedForCamera() {
        showMessage(getString(R.string.permission_camera_denied))
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    fun showNeverAskForCamera() {
        showMessage(getString(R.string.permission_camera_neverask))
    }

    private fun shareApp() {
        val shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText(getString(R.string.message_share_app))
                .intent
        if (shareIntent.resolveActivity(packageManager) != null) {
            logEvent(FirebaseAnalytics.Event.SHARE, mapOf(FirebaseAnalytics.Param.CONTENT_TYPE to "the app"))
            startActivity(shareIntent)
        } else {
            showMessage(getString(R.string.error_message_share_app))
        }
    }

    private fun setupView() {
        binding.drawer.addDrawerListener(drawerToggle)
        binding.drawer.post(syncDrawerStateRunnable)
        binding.drawer.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START)
        binding.ticketsCircles.adapter = ticketsCirclesAdapter
        binding.ticketsCircles.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                        state: RecyclerView.State?) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.left = ticketsSpace
            }
        })
        binding.navigation.setNavigationItemSelectedListener {
            binding.drawer.closeDrawer(binding.navigation)
            onOptionsItemSelected(it)
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        ticketsCirclesAdapter.invoicesCount = 0
        binding.balance.text = Constants.CURRENCY_FORMAT.format(0F)
        binding.tickets.text = getString(R.string.tickets_acquired, 0)
        binding.raffleDate.text = "--/--/--"
    }

}
