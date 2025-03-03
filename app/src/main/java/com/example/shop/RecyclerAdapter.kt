package com.example.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.databinding.ShopItemBinding

class RecyclerAdapter(val items: ArrayList<ShopItem>) :
    RecyclerView.Adapter<RecyclerAdapter.ShopItemViewHolder>() {
    class ShopItemViewHolder(val binding: ShopItemBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ShopItemBinding.inflate(inflater, parent, false)

        return ShopItemViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(
        holder: ShopItemViewHolder,
        position: Int
    ) {
        val item = items[position]

        with(holder.binding) {
            itemName.text = item.getName()
        }
    }
}
