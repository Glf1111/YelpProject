package com.glimiafernandez.yelpProject

import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.glimiafernandez.yelpProject.databinding.ActivityScrollingDetailBinding
import com.glimiafernandez.yelpProject.yelpModels.RestaurantDetailAdapter
import com.glimiafernandez.yelpProject.yelpModels.RestaurantDetailData
import com.glimiafernandez.yelpProject.yelpModels.YelpDetailsService
import com.glimiafernandez.yelpProject.yelpModels.YelpRestaurant
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.format.DateTimeFormatter


private const val TAG = "ScrollingActivity"
private const val BASE_URL = " https://api.yelp.com/v3/"


class ScrollingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollingDetailBinding
    private lateinit var restaurant: YelpRestaurant
    private val restaurantDetailData = mutableListOf<RestaurantDetailData>()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrollingDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)




        restaurant = intent.getSerializableExtra(DETAILS) as YelpRestaurant







        val mainBackDrop = findViewById<ImageView>(R.id.main_backdrop)
        Glide.with(this).load(restaurant.imageUrl).into(mainBackDrop)


        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = restaurant.name
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Added to Fav", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show()
        }


        val adapterDetail = RestaurantDetailAdapter(this,restaurantDetailData)

        val rvView = findViewById<RecyclerView>(R.id.rvScrollingView)
        rvView.adapter = adapterDetail
        rvView.layoutManager = LinearLayoutManager(this)


        val id = restaurant.id
        Log.i(TAG, "id $id")


        val retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val yelpDetails = retrofit.create(YelpDetailsService :: class.java)
        yelpDetails.moreInfo(id,"Bearer $API_KEY").enqueue(object : Callback<RestaurantDetailData> {

            override fun onResponse(call: Call<RestaurantDetailData>, response: Response<RestaurantDetailData>) {
                Log.i(TAG, "response $response")
                val body = response.body()
                if (body == null){
                    Log.w(TAG, "Did not receive valid response body from Yelp Api...existing")
                    return
                }
                restaurantDetailData.addAll(listOf(body))
                Log.i(TAG, restaurantDetailData.toString())
                adapterDetail.notifyDataSetChanged()


            }

            override fun onFailure(call: Call<RestaurantDetailData>, t: Throwable) {
                Log.i(TAG,"onFailure $t")
            }

        })













    }




    }



