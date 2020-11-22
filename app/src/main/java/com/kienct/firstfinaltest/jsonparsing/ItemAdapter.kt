package com.kienct.firstfinaltest.jsonparsing


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kienct.firstfinaltest.R

class ItemAdapter(private val list: List<Item>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.bind(list[position])
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Item) {
            val name: TextView = itemView.findViewById(R.id.tvName)
            val image : TextView = itemView.findViewById(R.id.tvImage)
            val checked: TextView = itemView.findViewById(R.id.tvChecked)
            name.text = item.name
            image.text = item.image
            checked.text = item.checked.toString()
        }
    }
}