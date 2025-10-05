package com.example.lab_week_06

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatModel

class CatAdapter(private val layoutInflater: LayoutInflater,
                 private val imageLoader: ImageLoader,
                 private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<CatViewHolder>() {
    //Mutable List for storing all the List data
    private val cats = mutableListOf<CatModel>()
    //Delete callback Instantiation
    val swipeToDeleteCallback = SwipeToDeleteCallback()
    //A function to set the mutable list
    fun setData(newCats: List<CatModel>) {
        cats.clear()
        cats.addAll (newCats)
        //This is used to tell the adapter that there's a data change in the mutable list
        notifyDataSetChanged()
    }
    fun removeItem(position: Int){
        cats.removeAt(position)
        notifyItemRemoved(position)
    }

    //A view holder is used to bind the data to the layout views
    //onCreateViewHolder is instantiating the view holder it self
    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int):
        CatViewHolder {
        val view = layoutInflater.inflate(R.layout.item_list, parent, false)
        return CatViewHolder(view, imageLoader, onClickListener)
    }
    //This is used to get the amount of data/item in the List
    override fun getItemCount() = cats.size
    //This is used to bind each data to each layout views
    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        //The holder parameter stores our previously created ViewHolder
        // The holder.bindData function is declared in the CatViewHolder
            holder.bindData(cats[position])
        }

        //Declare an OnClickListener Interface
        interface OnClickListener {
            fun onItemClick(cat: CatModel)
        }
    //you can declare a class inside a class using the inner keyword
    //declare a class for the swipe functionality
    inner class SwipeToDeleteCallback : ItemTouchHelper.SimpleCallback(
        0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
        //this is used if item list can be moved
        //Since we dont need that, we can set to false
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = false

        //this is used to determine which direction are allowed
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ) = if (viewHolder is CatViewHolder) {
            //here if we're not touching our phone, left and right are allowed
            makeMovementFlags(
                ItemTouchHelper.ACTION_STATE_IDLE,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                //here if we're swiping our phone, left and right are allowed
            ) or makeMovementFlags(
                ItemTouchHelper.ACTION_STATE_SWIPE,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            )
            //other gestures are not allowed (Drag, etc.)
        }else{
            0
        }

        //this is used for swipe detection
        //if a swipe is detected, then remove item
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            removeItem(position)
        }
    }
}