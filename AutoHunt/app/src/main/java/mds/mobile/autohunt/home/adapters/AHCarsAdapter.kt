package mds.mobile.autohunt.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import mds.mobile.autohunt.R
import mds.mobile.autohunt.databinding.AHCarItemDataBinding
import mds.mobile.autohunt.home.models.AHCar
import mds.mobile.autohunt.home.viewModels.AHCarItemViewModel

class AHCarsAdapter(
    private val carsList: ArrayList<AHCar>
) : RecyclerView.Adapter<AHCarsAdapter.CarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = DataBindingUtil.inflate<AHCarItemDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_car,
            parent,
            false
        )
        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(carsList[position])
    }

    override fun getItemCount(): Int = carsList.size

    inner class CarViewHolder(private val binding: AHCarItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(car: AHCar) {
            val viewModel = AHCarItemViewModel(car)
            binding.viewModel = viewModel
        }
    }
}