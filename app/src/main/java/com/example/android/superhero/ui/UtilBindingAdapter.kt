package com.example.android.superhero.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.android.superhero.R

@BindingAdapter("loading")
fun bindLoading(view: View, isLoading: Boolean) {
    if (isLoading)
        view.visibility = View.VISIBLE
    else
        view.visibility = View.GONE
}

@BindingAdapter("error")
fun bindError(textView: TextView, error: String?) {
    if (error != null)
        textView.visibility = View.VISIBLE
    else
        textView.visibility = View.GONE
}

@BindingAdapter("text")
fun bindText(textView: TextView, text: String) {
    textView.text = text
}

@BindingAdapter("image")
fun bindImagePath(imageView: ImageView, poster_path: String?) {
    poster_path?.let {
        Glide.with(imageView)
            .load(poster_path)
            .apply(
                RequestOptions()
                    .transform(FitCenter(), RoundedCorners(16))
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imageView)
    }
}