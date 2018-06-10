package br.com.danielsan.notafiscalcidada.features.authentication

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import br.com.danielsan.notafiscalcidada.R
import br.com.danielsan.notafiscalcidada.base.AnalyticsActivity
import br.com.danielsan.notafiscalcidada.databinding.ActivityAuthBinding
import br.com.danielsan.notafiscalcidada.features.main.MainActivity
import br.com.ilhasoft.support.validation.Validator
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.android.AndroidInjection
import io.rapidpro.sdk.FcmClient
import javax.inject.Inject

/**
 * Created by daniel on 18/08/17.
 */
class AuthActivity : AnalyticsActivity(), AuthContract {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, AuthActivity::class.java)
        }
    }

    @Inject
    lateinit var presenter: AuthPresenter
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityAuthBinding>(this, R.layout.activity_auth)
    }
    private val validator by lazy { Validator(binding) }
    val onClickAuthenticate by lazy {
        View.OnClickListener {
            presenter.onClickAuthenticate(binding.editCpf.text.toString())
        }
    }
    val onClickUpdate by lazy {
        View.OnClickListener {
            presenter.onClickUpdate(binding.editCpf.text.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding.apply {
            activity = this@AuthActivity
            presenter = this@AuthActivity.presenter
            editCpf.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(binding.root.windowToken, 0)
                    action.performClick()
                    action.requestFocus()
                    return@OnEditorActionListener true
                }
                false
            })
        }
        logEvent("${FirebaseAnalytics.Event.LOGIN}_begin", mapOf())
        FcmClient.requestFloatingPermissionsIfNeeded(this)
        presenter.attachView(this)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun validate(): Boolean = validator.validate()

    override fun navigateToNoAccountOrDoubt() {
        logEvent(FirebaseAnalytics.Event.SIGN_UP, mapOf())
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://nfcidada.sefaz.al.gov.br")))
    }

    override fun navigateToUpdate(authorizationUrl: String) {
        binding.update = true
        val textUpdate = SpannableStringBuilder(getString(R.string.message_authorize_app, authorizationUrl))
        val indexStart = textUpdate.toString().indexOf("http")
        val indexEnd = textUpdate.toString().indexOf("\n", indexStart)
        textUpdate.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.medium_slate_blue)),
                           indexStart, indexEnd, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        textUpdate.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(textUpdate.substring(indexStart, indexEnd))))
            }
            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
            }
        }, indexStart, indexEnd, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        binding.text.movementMethod = LinkMovementMethod.getInstance()
        binding.text.text = textUpdate

        val cpf = binding.editCpf.toString().replace(Regex("[\\D]"), "")
        logEvent("${FirebaseAnalytics.Event.LOGIN}_auth", mapOf(FirebaseAnalytics.Param.ITEM_ID to cpf))
    }

    override fun navigateToMain() {
        val cpf = binding.editCpf.toString().replace(Regex("[\\D]"), "")
        logEvent(FirebaseAnalytics.Event.LOGIN, mapOf(FirebaseAnalytics.Param.ITEM_ID to cpf))
        startActivity(MainActivity.createIntent(this))
        finish()
    }

    override fun showDefaultUpdateAuthStatus() {
        showMessage(getString(R.string.error_message_update_authentication))
    }

}
