package mds.mobile.autohunt.utils

import androidx.databinding.BindingAdapter
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