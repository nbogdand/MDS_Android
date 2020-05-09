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


class AHCarSimpleAdapter(
    private val list: ArrayList<AHCar>
) : RecyclerView.Adapter<AHCarSimpleAdapter.CarViewHolder>() {

    var onClick: ((carId: Int) -> Unit)? = null

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
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

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
}