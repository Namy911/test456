package com.example.myapplication.ui.settings

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.model.CityItem
import com.example.myapplication.databinding.CityItemBinding

class CityAdapter : ListAdapter<CityItem, CityAdapter.CityViewHolder>(diff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(
            CityItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CityViewHolder( private val binding: CityItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(city: CityItem){
            binding.cityName.text = city.name
            binding.cityAddress.text = city.address
        }
    }
}

internal val diff = object : DiffUtil.ItemCallback<CityItem>() {
    override fun areItemsTheSame(oldItem: CityItem, newItem: CityItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CityItem, newItem: CityItem): Boolean {
        return oldItem == newItem
    }
}
