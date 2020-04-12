package mds.mobile.autohunt.home.views.fragments.userData

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import mds.mobile.autohunt.R
import mds.mobile.autohunt.databinding.AHUserDataFragmentBinding
import mds.mobile.autohunt.home.views.fragments.AHHomeBaseFragment

class AHUserDataFragment : AHHomeBaseFragment() {

    private lateinit var binding: AHUserDataFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_user_data,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        return  binding.root
    }
}