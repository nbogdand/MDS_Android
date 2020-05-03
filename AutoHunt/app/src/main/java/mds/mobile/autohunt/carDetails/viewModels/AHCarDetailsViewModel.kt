package mds.mobile.autohunt.carDetails.viewModels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import mds.mobile.autohunt.home.models.AHCar

class AHCarDetailsViewModel: ViewModel() {
    var car = ObservableField(AHCar.getPlaceholder())
}