package mds.mobile.autohunt.home.views.fragments.carList

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import mds.mobile.autohunt.R
import mds.mobile.autohunt.carDetails.views.activities.AHCarActivity
import mds.mobile.autohunt.databinding.AHCarListFragmentBinding
import mds.mobile.autohunt.home.adapters.AHCarsAdapter
import mds.mobile.autohunt.home.models.AHCar
import mds.mobile.autohunt.home.views.fragments.AHHomeContainerFragment
import mds.mobile.autohunt.utils.AHConstants.Keys.FRAGMENT_OBJECT_ID

class AHCarListFragment : AHHomeContainerFragment() {

    private lateinit var binding: AHCarListFragmentBinding
    private lateinit var adapter: AHCarsAdapter

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

        setupAdapter()

        binding.adapter = adapter
    }

    private fun setupAdapter() {
        adapter = AHCarsAdapter(carsList = generateList())
        adapter.onClick = { carId ->
            goToCarDetail(carId)
        }
    }

    private fun goToCarDetail(
        carId: String?
    ) = activity?.let {
        val intent = Intent(it, AHCarActivity::class.java)
        intent.putExtra(FRAGMENT_OBJECT_ID, carId)
        startActivity(intent)
    }

    private fun generateList(): ArrayList<AHCar> {
        val carList = ArrayList<AHCar>()
        for( x in 0 until 50) {
            carList.add(AHCar.getPlaceholder())
        }
        return carList
    }
}