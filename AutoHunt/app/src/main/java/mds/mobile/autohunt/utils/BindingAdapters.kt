package mds.mobile.autohunt.utils

import android.widget.ImageView
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