package mds.mobile.autohunt.authentication.views

import android.os.Binder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mds.mobile.autohunt.R
import mds.mobile.autohunt.authentication.viewModels.AHRegisterFragmentViewModel
import mds.mobile.autohunt.databinding.AHRegisterFragmentBinding

class AHRegisterFragment : Fragment() {

    companion object {
        fun newInstance() = AHRegisterFragment()
    }

    private val viewModel by lazy {
        ViewModelProvider(this).get(AHRegisterFragmentViewModel::class.java)
    }
    private lateinit var binding: AHRegisterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_register,
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
}