package com.example.shop

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.databinding.ShopItemBinding

class RecyclerAdapter(var items: ArrayList<ShopItem>, var context: ShopListFragment) :
    RecyclerView.Adapter<RecyclerAdapter.ShopItemViewHolder>() {


    class ShopItemViewHolder(private var binding: ShopItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val itemName = binding.itemName
        val itemImage = binding.itemImage
        val deleteBtn = binding.deleteBtn
    }

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
        Log.d("TEST", "Setting attributes for... ${item.getName()}")
        holder.itemName.text = item.getName()
        holder.itemImage.setImageURI(item.getUri())

        holder.deleteBtn.setOnClickListener {
            val index = item.getIndex()

            Log.d("TEST", "INDEX DELETE: ${index}")
            items.removeAt(index)

            itemDeleted(items, index)
            context.notifyFragment(items)
        }
    }

    fun itemAdded(items: ArrayList<ShopItem>) {
        this.items = items
        notifyItemInserted(items.size)
    }

    fun itemDeleted(items: ArrayList<ShopItem>, index: Int) {
        this.items = items
        notifyItemRemoved(index)
    }
}
