package com.example.lab_week_06

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatModel

class CatAdapter(private val layoutInflater: LayoutInflater, private val
imageLoader: ImageLoader) : RecyclerView.Adapter<CatViewHolder>() {
    //Mutable List for storing all the List data
    private val cats = mutableListOf<CatModel>()
    //A function to set the mutable list
    fun setData(newCats: List<CatModel>) {
        cats.clear()
        cats.addAll (newCats)
        //This is used to tell the adapter that there's a data change in the mutable list
        notifyDataSetChanged()
    }
//A view holder is used to bind the data to the layout views
//onCreateViewHolder is instantiating the view holder it self
override fun onCreateViewHolder (parent: ViewGroup, ViewType: Int):
    CatViewHolder {
    val view = layoutInflater.inflate(R.layout.item_list, parent, false)
    return CatViewHolder(view, imageLoader)
}
//This is used to get the amount of data/item in the List
override fun getItemCount() = cats.size
//This is used to bind each data to each layout views
override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
    //The holder parameter stores our previously created ViewHolder
    // The holder.bindData function is declared in the CatViewHolder
        holder.bindData(cats[position])
    }
}