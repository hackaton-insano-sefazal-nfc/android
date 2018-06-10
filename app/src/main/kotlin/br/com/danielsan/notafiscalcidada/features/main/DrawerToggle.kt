package br.com.danielsan.notafiscalcidada.features.main

import android.app.Activity
import android.graphics.Color
import android.support.design.widget.AppBarLayout
import android.support.v4.view.ViewCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.util.TypedValue
import android.view.View
import br.com.danielsan.notafiscalcidada.R

/**
 * Created by daniel on 29/09/17.
 */
class DrawerToggle(
        activity: Activity,
        drawer: DrawerLayout,
        toolbar: Toolbar,
        private val appBar: AppBarLayout
) : ActionBarDrawerToggle(
        activity,
        drawer,
        toolbar,
        R.string.app_name,
        R.string.app_name
) {

    private val appBarElevation: Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4f, activity.resources.displayMetrics).toInt()

    init {
        drawerArrowDrawable.color = Color.WHITE
    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
        super.onDrawerSlide(drawerView, slideOffset)
        ViewCompat.setElevation(appBar, slideOffset * appBarElevation)
    }

}
