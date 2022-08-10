package com.glimiafernandez.yelpProject.yelpModels

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import com.glimiafernandez.yelpProject.yelpModels.YelpRestaurant
import retrofit2.http.Path


interface YelpDetailsService {

    @GET("businesses/{id}")

    fun moreInfo(
        @Path("id") id: String,
        @Header("Authorization") authHeader: String

    ):Call<RestaurantDetailData>

}