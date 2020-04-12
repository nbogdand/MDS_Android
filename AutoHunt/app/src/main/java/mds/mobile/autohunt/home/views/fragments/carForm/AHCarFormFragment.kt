package mds.mobile.autohunt.home.views.fragments.carForm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import mds.mobile.autohunt.R
import mds.mobile.autohunt.databinding.AHCarFormFragmentBinding
import mds.mobile.autohunt.home.views.fragments.AHHomeBaseFragment

class AHCarFormFragment : AHHomeBaseFragment() {

    private lateinit var binding: AHCarFormFragmentBinding

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
}