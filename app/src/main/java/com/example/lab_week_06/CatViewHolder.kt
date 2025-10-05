package com.example.lab_week_06

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatBreed
import com.example.lab_week_06.model.CatModel
import com.example.lab_week_06.model.Gender

private val FEMALE_SYMBOL = "\u2640"
private val MALE_SYMBOL = "\u2642"
private const val UNKNOWN_SYMBOL = "?"
class CatViewHolder(
    private val containerView: View,
    private val imageLoader: ImageLoader,
    private val onClickListener: CatAdapter.OnClickListener
) :
    RecyclerView.ViewHolder(containerView) {
    //containerView is the container layout of each item List
    //Here findViewById is used to get the reference of each views inside the container
    private val catBiographyView: TextView by lazy {
        containerView.findViewById(R.id.cat_biography)
    }
    private val catBreedView: TextView by lazy {
        containerView.findViewById(R.id.cat_breed) }
    private val catGenderView: TextView by lazy {
        containerView.findViewById(R.id.cat_gender) }
    private val catNameView: TextView by lazy {
        containerView.findViewById(R.id.cat_name) }
    private val catPhotoView: ImageView by lazy {
        containerView.findViewById(R.id.cat_photo) }

    //This function is called in the adapter to provide the binding function
    fun bindData(cat: CatModel) {
       //Override the onClickListener function
        containerView.setOnClickListener {
        //Here we are using the onClickListener passed from the Adapter
            onClickListener.onItemClick(cat)
        }

        imageLoader.loadImage(cat.imageUrl,catPhotoView)
        catNameView.text = cat.name
        catBreedView.text = when (cat.breed){
            CatBreed.AmericanCurl ->  " American Curl"
            CatBreed.ExoticShorthair -> "Exotic Short Hair"
            CatBreed.BalineseJavanese -> "Balinese-Javanese"
            CatBreed.Siamese -> "Siamese"
            CatBreed.Persian -> "Persian"
            CatBreed.MaineCoon -> "Maine Coon"
            CatBreed.Bengal -> "Bengal"
            CatBreed.Sphynx -> "Sphynx"
            CatBreed.Ragdoll -> "Ragdoll"
            else -> "Unknown"
        }
        catBiographyView.text = cat.biography
        catGenderView.text = when (cat.gender){
            Gender.Female -> FEMALE_SYMBOL
            Gender.Male -> MALE_SYMBOL
            else -> UNKNOWN_SYMBOL
        }
    }

    //Declare an OnClickListener Interface
//    interface OnClickListener {
//        fun onClick(cat: CatModel)
//    }
}