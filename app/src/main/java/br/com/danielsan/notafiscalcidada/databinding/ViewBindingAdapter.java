package br.com.danielsan.notafiscalcidada.databinding;

import android.databinding.BindingAdapter;
import android.view.View;

public final class ViewBindingAdapter {

    @BindingAdapter(value = {"visible"})
    public static void setVisible(View view, boolean visible) {
        if (visible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

}
