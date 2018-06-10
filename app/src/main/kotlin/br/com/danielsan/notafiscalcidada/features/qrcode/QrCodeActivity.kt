package br.com.danielsan.notafiscalcidada.features.qrcode

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.PointF
import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import br.com.danielsan.notafiscalcidada.R
import br.com.danielsan.notafiscalcidada.base.AnalyticsActivity
import br.com.danielsan.notafiscalcidada.databinding.ActivityQrcodeBinding
import com.dlazaro66.qrcodereaderview.QRCodeReaderView
import com.google.firebase.analytics.FirebaseAnalytics

/**
 * Created by daniel on 20/08/17.
 */
class QrCodeActivity : AnalyticsActivity(), QRCodeReaderView.OnQRCodeReadListener {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, QrCodeActivity::class.java)
        }
    }

    private var runningRequest = false
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityQrcodeBinding>(this, R.layout.activity_qrcode)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setResult(Activity.RESULT_CANCELED)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        binding.qrdecoderview.apply {
            setQRDecodingEnabled(true)
            setAutofocusInterval(2000L)
            setOnQRCodeReadListener(this@QrCodeActivity)
        }
        binding.toolbar?.apply {
            title.text = getString(R.string.your_invoice)
            back.setOnClickListener { onBackPressed() }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.qrdecoderview.startCamera()
    }

    override fun onPause() {
        binding.qrdecoderview.stopCamera()
        super.onPause()
    }

    override fun onQRCodeRead(text: String, points: Array<out PointF>) {
        if (runningRequest) return
        runningRequest = true

        logEvent(FirebaseAnalytics.Event.VIEW_ITEM, mapOf(FirebaseAnalytics.Param.ITEM_VARIANT to "qrcode"))
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(text)))
        finish()
    }

}
