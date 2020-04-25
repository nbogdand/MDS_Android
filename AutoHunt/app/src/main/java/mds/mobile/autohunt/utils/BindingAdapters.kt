package mds.mobile.autohunt.utils

import android.R
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener

@BindingAdapter("navigationListener")
fun setNavigationListener(
    bubbleNavigation: BubbleNavigationConstraintView,
    listener: BubbleNavigationChangeListener?
) {
    listener?.let {
        bubbleNavigation.setNavigationChangeListener(it)
    }
}

@BindingAdapter("loadImageFromUrl")
fun setImageFromUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .into(imageView)
}

@BindingAdapter("setupSpinnerAdapter")
fun setupSpinnerAdapter(spinner: Spinner, list: ArrayList<String>){
    val spinnerAdapter =
        ArrayAdapter<String>(
            spinner.context,
            R.layout.simple_spinner_item,
            list
        )

    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    spinner.adapter = spinnerAdapter
}