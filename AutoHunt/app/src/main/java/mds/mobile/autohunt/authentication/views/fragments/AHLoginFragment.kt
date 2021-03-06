package mds.mobile.autohunt.authentication.views.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import mds.mobile.autohunt.R
import mds.mobile.autohunt.authentication.viewModels.AHLoginFragmentViewModel
import mds.mobile.autohunt.databinding.AHLoginFragmentBinding
import mds.mobile.autohunt.home.views.activities.AHHomeActivity

class AHLoginFragment : AHBaseAuthFragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
            AHLoginFragment()
    }

    private val viewModel by lazy {
        ViewModelProvider(this).get(AHLoginFragmentViewModel::class.java)
    }
    lateinit var binding: AHLoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<AHLoginFragmentBinding>(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        viewModel.goToRegister = { authNavigation?.goToRegister() }
        viewModel.onLoginSuccess = {
            goToHome()
            activity?.finish()
        }
    }

    private fun goToHome() = activity?.let{
        val intent = Intent(it, AHHomeActivity::class.java)
        startActivity(intent)
    }
}
