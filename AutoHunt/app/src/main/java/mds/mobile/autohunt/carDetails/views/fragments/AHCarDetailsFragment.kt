package mds.mobile.autohunt.carDetails.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mds.mobile.autohunt.R
import mds.mobile.autohunt.carDetails.viewModels.AHCarDetailsViewModel
import mds.mobile.autohunt.databinding.AHCarDetailsFragmentBinding
import mds.mobile.autohunt.utils.AHConstants.Keys.FRAGMENT_OBJECT_ID
import mds.mobile.autohunt.utils.viewModelFactory

class AHCarDetailsFragment: Fragment(){

    companion object {
        fun newInstance(bundle: Bundle): AHCarDetailsFragment {
            return AHCarDetailsFragment().apply {
                arguments = bundle
            }
        }
    }

    private lateinit var binding: AHCarDetailsFragmentBinding
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory {
            AHCarDetailsViewModel()
        }).get(AHCarDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_car_details,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}