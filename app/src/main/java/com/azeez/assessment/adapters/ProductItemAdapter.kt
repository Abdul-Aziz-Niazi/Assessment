package com.azeez.assessment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azeez.assessment.R
import com.azeez.assessment.daos.responses.Item
import kotlinx.android.synthetic.main.layout_item_product.view.*

class ProductItemAdapter : RecyclerView.Adapter<ProductItemAdapter.ViewHolder>() {
    var items = ArrayList<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Item) {
            itemView.apply {
                title.text = item.name
                subtitle.text = item.description
                price.text = String.format("%.1f", item.price)
            }
        }
    }


}