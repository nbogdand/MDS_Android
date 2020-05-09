package mds.mobile.autohunt.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mds.mobile.autohunt.R
import mds.mobile.autohunt.databinding.AHCarItemDataBinding
import mds.mobile.autohunt.home.models.AHCar
import mds.mobile.autohunt.home.viewModels.AHCarItemViewModel

class AHCarsAdapter(
) : PagedListAdapter<AHCar, AHCarsAdapter.CarViewHolder>(DIFF_CALLBACK) {

    var onClick: ((carId: String) -> Unit)? = null

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
        getItem(position)?.let { holder.bind(it) }
    }

    inner class CarViewHolder(private val binding: AHCarItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(car: AHCar) {
            val viewModel = AHCarItemViewModel(car)
            binding.viewModel = viewModel
            binding.cvContainer.setOnClickListener {
                onClick?.invoke(car.id ?: return@setOnClickListener)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AHCar>() {
            override fun areItemsTheSame(
                oldItem: AHCar,
                newItem: AHCar
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: AHCar,
                newItem: AHCar
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}