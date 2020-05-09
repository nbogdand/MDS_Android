package mds.mobile.autohunt.home.views.fragments.carList

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import mds.mobile.autohunt.R
import mds.mobile.autohunt.carDetails.views.activities.AHCarActivity
import mds.mobile.autohunt.databinding.AHCarListFragmentBinding
import mds.mobile.autohunt.home.adapters.AHCarSimpleAdapter
import mds.mobile.autohunt.home.adapters.AHCarsAdapter
import mds.mobile.autohunt.home.models.AHCar
import mds.mobile.autohunt.home.viewModels.AHCarListFragmentViewModel
import mds.mobile.autohunt.home.views.fragments.AHHomeContainerFragment
import mds.mobile.autohunt.shared.AHHomeSharedViewModel
import mds.mobile.autohunt.utils.AHConstants.Keys.FRAGMENT_OBJECT_ID
import mds.mobile.autohunt.utils.viewModelFactory

class AHCarListFragment : AHHomeContainerFragment() {

    private lateinit var binding: AHCarListFragmentBinding
    private lateinit var adapter: AHCarsAdapter
    private lateinit var simpleAdapter: AHCarSimpleAdapter
//    private var autoDisposable: CompositeDisposable
    private lateinit var viewModel: AHCarListFragmentViewModel
    private lateinit var sharedViewModel: AHHomeSharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_car_list,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupAdapter()
        setupObservers()

        binding.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
//        autoDisposable = CompositeDisposable()
        viewModel.refreshList()
    }

    override fun onStop() {
        super.onStop()
//        autoDisposable.dispose()
    }

    private fun setupAdapter() {
        adapter = AHCarsAdapter()
        adapter.onClick = { carId ->
            goToCarDetail(carId)
        }
    }

    private fun setupSimpleAdapter(list: ArrayList<AHCar>) {
        simpleAdapter = AHCarSimpleAdapter(list)
        simpleAdapter.onClick = { carId ->
            goToCarDetail(carId)
        }
        binding.adapter = simpleAdapter
        binding.notifyChange()
    }

    private fun setupObservers() {
        viewModel.itemPagedList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        sharedViewModel.mutableSelectedBrand.observe(viewLifecycleOwner, Observer {
            viewModel.getAllCarByBrand(
                brand = it,
                onSuccess = { list ->
                    setupSimpleAdapter(list)
                }
            )
        })
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory {
            AHCarListFragmentViewModel(
//                autoDisposable = autoDisposable
            )
        }).get(AHCarListFragmentViewModel::class.java)

        activity?.let {
            sharedViewModel = ViewModelProvider(it, viewModelFactory {
                AHHomeSharedViewModel()
            }).get(AHHomeSharedViewModel::class.java)
        }
    }

    private fun goToCarDetail(
        carId: Int?
    ) = activity?.let {
        val intent = Intent(it, AHCarActivity::class.java)
        intent.putExtra(FRAGMENT_OBJECT_ID, carId)
        startActivity(intent)
    }
}