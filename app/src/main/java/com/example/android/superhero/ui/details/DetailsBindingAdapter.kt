package com.example.android.superhero.ui.details

import android.content.res.Resources
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.superhero.R

@BindingAdapter("herosFirstAppearance")
fun bindHerosFirstAppearance(textView: TextView, firstAppearance: String?) {
    val value = firstAppearance.valOrUnknown(textView.resources)
    textView.text = textView.resources.getString(R.string.details_first_appearance, value)
}

@BindingAdapter("herosAlignment")
fun bindHerosAlignment(textView: TextView, alignment: String?) {
    val value = alignment.valOrUnknown(textView.resources)
    textView.text = textView.resources.getString(R.string.details_alignment, value)
}

@BindingAdapter("herosPublisher")
fun bindHerosPublisher(textView: TextView, publisher: String?) {
    val value = publisher.valOrUnknown(textView.resources)
    textView.text = textView.resources.getString(R.string.details_publisher, value)
}

@BindingAdapter(value = ["herosGAName", "herosGroupAffiliations"])
fun bindHerosGroupAffiliations(textView: TextView, name: String, groupAff: String?) {
    val value = groupAff.valOrUnknown(textView.resources)
    textView.text = textView.resources.getString(R.string.details_group_affiliation, name, value)
}

@BindingAdapter("herosGender")
fun bindHerosGender(textView: TextView, gender: String?) {
    val value = gender.valOrUnknown(textView.resources)
    textView.text = textView.resources.getString(R.string.details_gender, value)
}

@BindingAdapter(value = ["herosPower", "herosSpeed"])
fun bindHerosStats(textView: TextView, power: String?, speed: String?) {
    val powerVal = power.valOrUnknown(textView.resources)
    val speedVal = speed.valOrUnknown(textView.resources)

    textView.text = textView.resources.getString(R.string.details_stats, powerVal, speedVal)
}

fun String?.valOrUnknown(resources: Resources): String {
    if (isNullOrEmpty() || this == resources.getString(R.string.empty_char) || this == resources.getString(R.string.empty_null_string))
        return resources.getString(R.string.unknown)
    return this
}