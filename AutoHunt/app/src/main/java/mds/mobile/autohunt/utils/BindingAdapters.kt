package mds.mobile.autohunt.utils

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener
import mds.mobile.autohunt.R

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
fun setImageFromUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(url)
        .into(imageView)
}

@BindingAdapter("setupSpinnerAdapter")
fun setupSpinnerAdapter(spinner: Spinner, list: ArrayList<String>){
    val spinnerAdapter =
        ArrayAdapter<String>(
            spinner.context,
            android.R.layout.simple_spinner_item,
            list
        )

    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    spinner.adapter = spinnerAdapter
}

@BindingAdapter("isEditable")
fun setEditable(editText: EditText, isEditable: Boolean?) {
    isEditable?.let { editable ->
        editText.isClickable = editable
        editText.isFocusable = editable
        editText.isCursorVisible = editable
        editText.isFocusableInTouchMode = editable
    }
}

@BindingAdapter("editSave")
fun setEditSave(button: Button, editEnabled: Boolean?) {
    editEnabled?.let { enabled ->
        when (enabled) {
            true -> {
                button.text = "Save"
                button.setBackgroundResource(R.drawable.shape_button_moss_3_round)
            }
            false -> {
                button.text = "Edit"
                button.setBackgroundResource(R.drawable.shape_button_moss_alternative_round)
            }
        }
    }
}

@BindingAdapter("drawableTintColor")
fun setDrawableTint(textView: TextView, color: Int) {
    textView.compoundDrawableTintMode = PorterDuff.Mode.DST_OVER
    textView.compoundDrawableTintList = ColorStateList.valueOf(color)
}