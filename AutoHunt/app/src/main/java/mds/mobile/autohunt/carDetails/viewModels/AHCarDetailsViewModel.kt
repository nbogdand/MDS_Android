package mds.mobile.autohunt.carDetails.viewModels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mds.mobile.autohunt.carDetails.data.repository.AHCarAPIRepository
import mds.mobile.autohunt.home.models.AHCar
import mds.mobile.autohunt.utils.provideInfo

class AHCarDetailsViewModel(
    private val carId: Int
): ViewModel() {
    var car = ObservableField<AHCar>()

    fun getCarDetails(
        onStart: () -> Unit,
        onFinished: () -> Unit
    ) {
        onStart.invoke()
        val disposable = AHCarAPIRepository.getCarDetails(carId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ carDetails ->
                car.set(carDetails)
                onFinished.invoke()
            },{ error ->
                "Get card details error: ${error.message}".provideInfo()
                onFinished.invoke()
            })
    }
}