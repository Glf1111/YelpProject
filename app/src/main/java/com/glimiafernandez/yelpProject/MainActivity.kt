package com.glimiafernandez.yelpProject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.glimiafernandez.yelpProject.yelpModels.YelpDataClasses
import com.glimiafernandez.yelpProject.yelpModels.YelpRestaurant
import com.glimiafernandez.yelpProject.yelpModels.YelpService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "MainActivity"
private const val BASE_URL = " https://api.yelp.com/v3/"
const val API_KEY = "G-N9gW0oVN-i6UPOThN_yvQ9ZvNDQJ17fltdBH32nrlM6d6lsrcKZI1JOm1q0v1UtrJfkjzX2At77-syWbcdZrixsARWUHW58NJaOSaM7S1qOFWkzmGAFl362_zXYnYx"
const val DETAILS = "Details"

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvRestaurant = findViewById<RecyclerView>(R.id.rvRestaurants)
        val restaurants = mutableListOf<YelpRestaurant>()
        val adapter = RestaurantAdapter(this, restaurants,object :RestaurantAdapter.OnClickListener{
            override fun onItemClick(position: Int) {
                Log.i(TAG, "position $position")
                val intent = Intent(this@MainActivity, ScrollingActivity ::class.java )
                intent.putExtra(DETAILS,restaurants[position])
                startActivity(intent)




            }

        })
        rvRestaurant.adapter = adapter
        rvRestaurant.layoutManager = LinearLayoutManager(this)

        val retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val yelpService = retrofit.create(YelpService ::class.java)

        //Asynchronous task
        yelpService.searchRestaurants("Bearer $API_KEY","Pizza Restaurants","Madrid").enqueue(object :Callback<YelpDataClasses>{
            override fun onResponse(call: Call<YelpDataClasses>, response: Response<YelpDataClasses>) {
                Log.i(TAG,"response $response")
                val body = response.body()
                if(body == null){
                    Log.w(TAG, "Did not receive valid response body from Yelp Api...existing")
                    return
                }
                restaurants.addAll(body.restaurants)
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<YelpDataClasses>, t: Throwable) {
                Log.i(TAG,"onFailure $t")
            }

        })




    }

}
