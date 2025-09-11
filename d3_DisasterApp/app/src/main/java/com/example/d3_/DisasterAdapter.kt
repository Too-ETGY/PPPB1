package com.example.d3_

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.d3_.databinding.ItemDisasterBinding


class DisasterAdapter(private  val listDisaster: List<Disaster>):
    RecyclerView.Adapter<DisasterAdapter.ItemDisasterViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemDisasterViewHolder {
        val binding = ItemDisasterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ItemDisasterViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ItemDisasterViewHolder,
        position: Int
    ) {
        holder.bind(listDisaster[position])
    }

    override fun getItemCount(): Int {
        return  listDisaster.count()
    }

    inner class ItemDisasterViewHolder(private val binding: ItemDisasterBinding) :
        RecyclerView.ViewHolder(binding.root){
//        function to handle how to bind data
        fun bind(data: Disaster) {
            with(binding){
                txtDisasterName.setText(data.name)
                txtDisasterTypes.setText(data.types)
            }
        }
    }
}
