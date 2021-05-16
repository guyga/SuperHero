package com.example.android.superhero.ui.details

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.superhero.R

@BindingAdapter("herosFirstAppearance")
fun bindHerosFirstAppearance(textView: TextView, firstAppearance: String?) {
    var value = firstAppearance
    if (value == null || value == "-")
        value = textView.resources.getString(R.string.unknown)
    textView.text = textView.resources.getString(R.string.details_first_appearance, value)
}

@BindingAdapter("herosAlignment")
fun bindHerosAlignment(textView: TextView, alignment: String?) {
    var value = alignment
    if (value == null || value == "-")
        value = textView.resources.getString(R.string.unknown)
    textView.text = textView.resources.getString(R.string.details_alignment, value)
}

@BindingAdapter("herosPublisher")
fun bindHerosPublisher(textView: TextView, publisher: String?) {
    var value = publisher
    if (value == null || value == "-")
        value = textView.resources.getString(R.string.unknown)
    textView.text = textView.resources.getString(R.string.details_publisher, value)
}