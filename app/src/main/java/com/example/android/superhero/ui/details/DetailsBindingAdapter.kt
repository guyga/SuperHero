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

@BindingAdapter(value = ["herosGAName", "herosGroupAffiliations"])
fun bindHerosGroupAffiliations(textView: TextView, name: String, groupAff: String?) {
    var value = groupAff
    if (value == null || value == "-")
        value = textView.resources.getString(R.string.unknown)

    textView.text = textView.resources.getString(R.string.details_group_affiliation, name, value)
}

@BindingAdapter("herosGender")
fun bindHerosGender(textView: TextView, gender: String?) {
    var value = gender
    if (value == null || value == "-")
        value = textView.resources.getString(R.string.unknown)

    textView.text = textView.resources.getString(R.string.details_gender, value)
}

@BindingAdapter(value = ["herosPower", "herosSpeed"])
fun bindHerosStats(textView: TextView, power: String?, speed: String?) {
    var powerVal = power
    if (powerVal == null || powerVal == "null")
        powerVal = textView.resources.getString(R.string.unknown)
    var speedVal = speed
    if (speedVal == null || speedVal == "null")
        speedVal = textView.resources.getString(R.string.unknown)

    textView.text = textView.resources.getString(R.string.details_stats, powerVal, speedVal)
}