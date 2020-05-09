package mds.mobile.autohunt.home.viewModels

import android.view.View
import android.widget.AdapterView
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mds.mobile.autohunt.utils.AHCarModels
import mds.mobile.autohunt.utils.provideInfo

class AHCarFormFragmentViewModel() : ViewModel() {

    val brands = ArrayList<String>().apply {
        addAll(getCarBrands())
    }

    val modelsObservable = ObservableField<ArrayList<String>>().apply {
        set(getCarModels())
    }

    val mutableSelectedBrand = MutableLiveData<String>()
    val mutableSelectedModel = MutableLiveData<String>()

    val brandsItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {}

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            mutableSelectedBrand.value = brands[position]
            modelsObservable.set(getCarModels())
            "Am selectat ${brands[position]}".provideInfo()
        }
    }
    val modelsItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {}

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            mutableSelectedModel.value = modelsObservable.get()?.get(position)
            "Am selectat ${modelsObservable.get()?.get(position)}".provideInfo()
        }
    }

    private fun getCarBrands(): ArrayList<String> {
        AHCarModels.carBrands?.let { return it } ?: kotlin.run {
            val brands = ArrayList<String>()
            brands.add("Audi")
            brands.add("BMW")
            brands.add("Honda")
            brands.add("Toyota")

            return brands
        }
    }

    private fun getCarModels(): ArrayList<String> =
        AHCarModels.carModels?.find {
            it.brand == mutableSelectedBrand?.value
        }?.models ?: ArrayList()
}