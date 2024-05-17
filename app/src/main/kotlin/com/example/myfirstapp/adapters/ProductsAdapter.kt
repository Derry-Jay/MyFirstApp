package com.example.myfirstapp.adapters

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.R
import com.example.myfirstapp.extensions.bindImage
import com.example.myfirstapp.extensions.isEmpty
import com.example.myfirstapp.extensions.showSnackBar
import com.example.myfirstapp.models.Product

class ProductsAdapter(private val pList: ArrayList<Product>) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.products_list_item_widget, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        println(Log.i("Products Count",pList.size.toString()))
        return pList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = pList[position]

        holder.imageView = holder.imageView.bindImage(if (product.thumbnail.isEmpty) product.images[0] else product.thumbnail)

        holder.imageView.adjustViewBounds = true

        // sets the text to the textview from our itemHolder class
        holder.textViewTitle.text = product.title

        holder.textViewDescription.text = product.description

        holder.textViewTitle.setOnClickListener {
            holder.imageView.showSnackBar(product.title, Toast.LENGTH_SHORT, product.category.name, null,null)
        }

        holder.textViewDescription.setOnClickListener {
        }
    }

    // Holds the views for adding it to image and text
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
    }
}