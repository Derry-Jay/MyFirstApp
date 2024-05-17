package com.example.myfirstapp.adapters

import android.view.View
import android.widget.Toast
import android.view.ViewGroup
import android.widget.TextView
import com.example.myfirstapp.R
import android.view.LayoutInflater
import com.example.myfirstapp.models.Item
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.extensions.showSnackBar

class ItemsAdapter(private val iList: ArrayList<Item>) :
    RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_unit_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Item = iList[position]

        holder.textViewTitle.text = item.title ?: ""

        holder.textViewDescription.text = item.description ?: ""

        holder.textViewCategory.text = item.category?.name ?: ""

        holder.textViewCreatedBy.text = item.createdBy?.name ?: ""

        holder.itemView.setOnClickListener {
            holder.itemView.showSnackBar(item.title ?: "", Toast.LENGTH_SHORT, item.description, null,null)
        }
    }

    override fun getItemCount(): Int {
        println("Size: " + iList.size)
        return iList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewCategory: TextView = itemView.findViewById(R.id.textViewCategory)
        val textViewCreatedBy: TextView = itemView.findViewById(R.id.textViewCreatedBy)
        val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
    }
}