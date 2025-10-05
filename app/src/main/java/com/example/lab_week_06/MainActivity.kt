package com.example.lab_week_06

import android.app.AlertDialog
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatBreed
import com.example.lab_week_06.model.CatModel
import com.example.lab_week_06.model.Gender


class MainActivity : AppCompatActivity() {
    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recycler_view)
    }

    private val catAdapter: CatAdapter by lazy {
        //Glide is used here to load the image
        //Here we are passing rhe onClickListener function to the Adapter
        CatAdapter(layoutInflater, GlideImageLoader(this), object :
        CatAdapter.OnClickListener{
            //when this is triggered, the pop up dialog will be shown
            override  fun onItemClick(cat: CatModel) = showSelectionDialog(cat)
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Setup the adapter fir the recycler view
        recyclerView.adapter =catAdapter

        //Setup the layout manager for the recycler view
        //A layout manager is used to set the structure of the item views
        //For this tutorial, we're using the vertical linear structure
        recyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)

        //instantiate ItemTouchHelper for the swipe to delete callback and
        //attach it to the recycler view
        val itemTouchHelper = ItemTouchHelper(catAdapter.swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        //Add data to the model list in the adapter
        catAdapter.setData(
            listOf(
                CatModel(
                    Gender.Male,
                    CatBreed.BalineseJavanese,
                    "Fred",
                    "Silent and deadly",
                    "https://cdn2.thecatapi.com/images/7dj.jpg"
                ),
                CatModel(
                    Gender.Female,
                    CatBreed.ExoticShorthair,
                    "Wilma",
                    "Cuddly assassin",
                    "https://cdn2.thecatapi.com/images/egv.jpg"
                ),
                CatModel(
                    Gender.Unknown,
                    CatBreed.AmericanCurl,
                    "Curious George",
                    "Award winning investigator",
                    "https://cdn2.thecatapi.com/images/bar.jpg"
                ),
                CatModel(
                    Gender.Male,
                    CatBreed.Siamese,
                    "Tom",
                    "Likes to chase mice",
                    "https://cdn2.thecatapi.com/images/a2b.jpg"
                ),
                CatModel(
                    Gender.Female,
                    CatBreed.Persian,
                    "Garfield",
                    "Loves lasagna",
                    "https://cdn2.thecatapi.com/images/4l3.jpg"
                ),
                CatModel(
                    Gender.Male,
                    CatBreed.MaineCoon,
                    "Simba",
                    "King of the house",
                    "https://cdn2.thecatapi.com/images/5p6.jpg"
                ),
                CatModel(
                    Gender.Female,
                    CatBreed.Bengal,
                    "Nala",
                    "Loves to play",
                    "https://cdn2.thecatapi.com/images/6i5.jpg"
                ),
                CatModel(
                    Gender.Male,
                    CatBreed.Sphynx,
                    "Felix",
                    "Needs a sweater",
                    "https://cdn2.thecatapi.com/images/7o4.jpg"
                ),
                CatModel(
                    Gender.Female,
                    CatBreed.Ragdoll,
                    "Sylvester",
                    "Is a bit of a scaredy cat",
                    "https://cdn2.thecatapi.com/images/8q4.jpg"
                ),
                CatModel(
                    Gender.Male,
                    CatBreed.BritishShorthair,
                    "Puss in Boots",
                    "Has a sword",
                    "https://cdn2.thecatapi.com/images/9p4.jpg"
                )
            )
        )
    }

    // This will create a pop up dialog when one of the items from the recycler view
    // is clicked
    private fun showSelectionDialog(cat: CatModel) {
        AlertDialog.Builder(this)
        //set the title for the dialog
            .setTitle("Cat Selected")
        //Set the message for the dialog
            .setMessage("You hace selected cat ${cat.name}")
        //Set if the OK Button should be enabled
            .setPositiveButton("OK"){_, _ -> }.show()
    }
}