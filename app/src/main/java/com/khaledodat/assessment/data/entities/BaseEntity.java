package com.khaledodat.assessment.data.entities;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class BaseEntity {

    @BindingAdapter(value = {"img", "placeHolder"}, requireAll = false)
    public static void loadThumbImage2(ImageView view, String imageUrl, Drawable placeHolder) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .placeholder(placeHolder)
                .into(view);
    }
}
