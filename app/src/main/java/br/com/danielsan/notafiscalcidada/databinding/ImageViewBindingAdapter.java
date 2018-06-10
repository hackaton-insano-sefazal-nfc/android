package br.com.danielsan.notafiscalcidada.databinding;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

public final class ImageViewBindingAdapter {

    @BindingAdapter(value = {"circleSrc"})
    public static void setVisible(ImageView imageView, @Nullable Drawable drawable) {
        if (drawable == null || !(drawable instanceof BitmapDrawable)) {
            imageView.setImageURI(null);
        } else {
            final Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            final RoundedBitmapDrawable roundedDrawable = RoundedBitmapDrawableFactory.create(imageView.getResources(), bitmap);
            roundedDrawable.setCircular(true);
            imageView.setImageDrawable(roundedDrawable);
        }
    }

}
