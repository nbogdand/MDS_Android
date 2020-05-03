package mds.mobile.autohunt.home.views.fragments.carForm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import mds.mobile.autohunt.R
import mds.mobile.autohunt.databinding.AHCarFormFragmentBinding
import mds.mobile.autohunt.home.viewModels.AHCarFormFragmentViewModel
import mds.mobile.autohunt.home.views.fragments.AHHomeContainerFragment
import mds.mobile.autohunt.utils.viewModelFactory

class AHCarFormFragment : AHHomeContainerFragment() {

    private lateinit var binding: AHCarFormFragmentBinding
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory {
            AHCarFormFragmentViewModel(
                brands = getBrands()
            )
        }).get(AHCarFormFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_car_form,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
    }

    private fun getBrands(): ArrayList<String> {
        val brands = ArrayList<String>()
        brands.add("Audi")
        brands.add("BMW")
        brands.add("Honda")
        brands.add("Toyota")

        return brands
    }
}