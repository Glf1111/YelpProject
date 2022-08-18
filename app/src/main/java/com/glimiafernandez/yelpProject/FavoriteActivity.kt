package com.glimiafernandez.yelpProject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


private const val TAG = "FavoriteActivity"
private const val FAVORITE = "FAVORITE"
private const val DETAILS = "Details"




class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)


     supportActionBar?.title = "Favorite Places"










    }
}