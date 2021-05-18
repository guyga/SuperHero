package com.example.android.superhero.ui

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("loading")
fun bindLoading(view: View, isLoading: Boolean) {
    if (isLoading)
        view.visibility = View.VISIBLE
    else
        view.visibility = View.GONE
}

@BindingAdapter("text")
fun bindText(textView: TextView, text: String) {
    textView.text = text
}