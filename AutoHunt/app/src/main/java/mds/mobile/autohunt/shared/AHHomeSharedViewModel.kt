package mds.mobile.autohunt.shared

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AHHomeSharedViewModel : ViewModel()  {
    val mutableSelectedBrand = MutableLiveData<String>()
    val mutableSelectedModel = MutableLiveData<String>()
}