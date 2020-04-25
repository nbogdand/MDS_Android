package mds.mobile.autohunt.home.views.fragments.userData

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import mds.mobile.autohunt.R
import mds.mobile.autohunt.databinding.AHUserDataFragmentBinding
import mds.mobile.autohunt.home.viewModels.AHUserDataFragmentViewModel
import mds.mobile.autohunt.home.views.fragments.AHHomeBaseFragment
import mds.mobile.autohunt.utils.viewModelFactory

class AHUserDataFragment : AHHomeBaseFragment() {

    private lateinit var binding: AHUserDataFragmentBinding
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory {
            AHUserDataFragmentViewModel()
        }).get(AHUserDataFragmentViewModel::class.java)
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
    }
}