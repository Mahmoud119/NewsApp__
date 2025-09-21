package com.route.newsc42.ui.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun bindImageFromUrl(imageView: ImageView, url: String?){
    Glide.with(imageView.context)
        .load(url)
        .into(imageView)
}