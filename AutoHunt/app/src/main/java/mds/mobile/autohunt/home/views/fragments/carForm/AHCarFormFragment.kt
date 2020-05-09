package mds.mobile.autohunt.home.views.fragments.carForm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mds.mobile.autohunt.R
import mds.mobile.autohunt.databinding.AHCarFormFragmentBinding
import mds.mobile.autohunt.home.viewModels.AHCarFormFragmentViewModel
import mds.mobile.autohunt.home.views.fragments.AHHomeContainerFragment
import mds.mobile.autohunt.shared.AHHomeSharedViewModel
import mds.mobile.autohunt.utils.AHCarModels
import mds.mobile.autohunt.utils.viewModelFactory

class AHCarFormFragment : AHHomeContainerFragment() {

    private lateinit var binding: AHCarFormFragmentBinding
    private lateinit var sharedViewModel: AHHomeSharedViewModel
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory {
            AHCarFormFragmentViewModel()
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

        setupSharedViewModel()
        setupObservers()
        binding.viewModel = viewModel
    }

    private fun setupSharedViewModel() = activity?.let{
        sharedViewModel = ViewModelProvider(it, viewModelFactory {
            AHHomeSharedViewModel()
        }).get(AHHomeSharedViewModel::class.java)
    }

    private fun setupObservers() {
        viewModel.mutableSelectedBrand.observe(viewLifecycleOwner, Observer {
            sharedViewModel.mutableSelectedBrand.value = it
        })
        viewModel.mutableSelectedModel.observe(viewLifecycleOwner, Observer {
            sharedViewModel.mutableSelectedModel.value = it
        })
    }
}