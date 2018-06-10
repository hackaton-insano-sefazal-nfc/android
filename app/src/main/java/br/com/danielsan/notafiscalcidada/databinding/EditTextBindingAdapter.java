package br.com.danielsan.notafiscalcidada.databinding;

import android.databinding.BindingAdapter;
import android.databinding.adapters.ListenerUtil;
import android.text.TextWatcher;
import android.widget.EditText;

import br.com.ilhasoft.support.validation.binding.TypeBindings;
import br.com.jansenfelipe.androidmask.MaskEditTextChangedListener;
import br.com.danielsan.notafiscalcidada.R;

public final class EditTextBindingAdapter {

    // FIXME: There is a bug on data-binding-validator: https://github.com/Ilhasoft/data-binding-validator/issues/10
    @BindingAdapter(value = {"validateType", "validateTypeMessage", "validateTypeAutoDismiss"}, requireAll = false)
    public static void setTypeValidationType(EditText editText, String fieldTypeText, String errorMessage, boolean autoDismiss) {
        TypeBindings.bindingTypeValidation(editText, fieldTypeText, errorMessage, autoDismiss);
    }

    @BindingAdapter(value = {"mask"})
    public static void setMask(EditText editText, String mask) {
        if (ListenerUtil.<TextWatcher>getListener(editText, R.id.text_watcher_mask_id) != null) {
            return;
        }
        final MaskEditTextChangedListener listener = new MaskEditTextChangedListener(mask, editText);
        editText.addTextChangedListener(listener);
        ListenerUtil.trackListener(editText, listener, R.id.text_watcher_mask_id);
    }

}
