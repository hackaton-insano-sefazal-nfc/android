package br.com.danielsan.notafiscalcidada.features.raffles

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.v7.app.AlertDialog
import br.com.danielsan.notafiscalcidada.Constants
import br.com.danielsan.notafiscalcidada.R
import br.com.danielsan.notafiscalcidada.base.AnalyticsActivity
import br.com.danielsan.notafiscalcidada.databinding.ActivityListBinding
import br.com.danielsan.notafiscalcidada.databinding.ViewBottomSheetRaffleBinding
import br.com.danielsan.notafiscalcidada.domain.raffle.Raffle
import br.com.danielsan.notafiscalcidada.domain.raffle.Ticket
import br.com.danielsan.notafiscalcidada.domain.raffle.WinningTicket
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Created by daniel on 01/10/17.
 */
class RafflesActivity : AnalyticsActivity(), RafflesContract {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, RafflesActivity::class.java)
        }
    }

    @Inject
    lateinit var presenter: RafflesPresenter
    private val adapter by lazy { RafflesAdapter(presenter) }
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityListBinding>(this, R.layout.activity_list)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding.recyclerView.adapter = adapter
        binding.toolbar?.apply {
            title.text = getText(R.string.raffles)
            back.setOnClickListener { finish() }
        }
        presenter.attachView(this)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun showRaffles(raffleList: List<Raffle>) {
        adapter.list.addAll(raffleList)
        adapter.notifyDataSetChanged()
    }

    override fun showRaffleOptions(raffle: Raffle) {
        val dialog = BottomSheetDialog(this)
        ViewBottomSheetRaffleBinding.inflate(layoutInflater, null, false).apply {
            yourTickets.setOnClickListener { presenter.onYourTicketsSelected(raffle); dialog.dismiss() }
            yourWinningTickets.setOnClickListener { presenter.onYourWinningTicketsSelected(raffle); dialog.dismiss() }
            dialog.setContentView(root)
        }
        dialog.show()

        val params = mutableMapOf(
                FirebaseAnalytics.Param.ITEM_ID to raffle.sequential,
                FirebaseAnalytics.Param.ITEM_NAME to raffle.description
        )
        raffle.dateOfRealization?.let { params.put(FirebaseAnalytics.Param.ITEM_VARIANT, it.toString()) }
        logEvent(FirebaseAnalytics.Event.VIEW_ITEM_LIST, params)
    }

    override fun showTickets(ticketList: List<Ticket>) {
        AlertDialog.Builder(this)
                .setTitle(getString(R.string.title_dialog_your_tickets))
                .setItems(ticketList.map { "${it.ticketNumber}" }.toTypedArray(), null)
                .setPositiveButton(android.R.string.ok, null)
                .show()
    }

    override fun showWithoutTickets() {
        AlertDialog.Builder(this)
                .setTitle(getString(R.string.tickets))
                .setMessage(getString(R.string.message_without_ticket_for_a_raffle))
                .setPositiveButton(android.R.string.ok, null)
                .show()
    }

    override fun showWinningTickets(winningTicketList: List<WinningTicket>) {
        val winningArray = winningTicketList.map { "Nº ${it.ticket?.ticketNumber}: ${Constants.CURRENCY_FORMAT.format(it.premiumAmount)}" }.toTypedArray()
        AlertDialog.Builder(this)
                .setTitle("${getString(R.string.your_winning_tickets)}:")
                .setItems(winningArray, null)
                .setPositiveButton(android.R.string.ok, null)
                .show()
    }

    override fun showWithoutWinningTickets() {
        AlertDialog.Builder(this)
                .setTitle(getString(R.string.winning_tickets))
                .setMessage(getString(R.string.message_without_winning_ticket_for_a_raffle))
                .setPositiveButton(android.R.string.ok, null)
                .show()
    }

}
