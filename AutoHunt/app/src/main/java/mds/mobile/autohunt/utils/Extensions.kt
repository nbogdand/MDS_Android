package mds.mobile.autohunt.utils

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
fun <VM : ViewModel> viewModelFactory(f: () -> VM) =
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(aClass: Class<T>): T = f() as T
    }

fun String.showToastMessage() {
    Toast.makeText(
        AHApplicationController.instance.applicationContext,
        this,
        Toast.LENGTH_LONG
    ).show()
}

@SuppressLint("LogNotTimber")
fun String.logErrorMessage(TAG: String = AHApplicationController.instance.packageName) {
    Log.e(TAG, this)
}

fun String.provideInfo() {
    this.logErrorMessage()
    this.showToastMessage()
}
