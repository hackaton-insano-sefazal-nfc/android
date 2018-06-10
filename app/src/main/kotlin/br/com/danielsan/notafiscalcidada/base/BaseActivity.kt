package br.com.danielsan.notafiscalcidada.base

import android.app.ProgressDialog
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import br.com.danielsan.notafiscalcidada.R

/**
 * Created by daniel on 18/08/17.
 */
abstract class BaseActivity : AppCompatActivity(), BaseView {

    protected val progressBar by lazy {
        ProgressDialog(this, R.style.AlertDialogTheme).apply {
            setMessage(getString(R.string.wait_a_momment))
            setCancelable(false)
        }
    }

    override fun showMessage(message: CharSequence) {
        val view = findViewById<ViewGroup>(android.R.id.content)?.getChildAt(0)
                ?: window.decorView.rootView
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressBar.show()
    }

    override fun dismissLoading() {
        progressBar.dismiss()
    }

}
