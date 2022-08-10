package com.glimiafernandez.yelpProject.yelpModels


import com.google.gson.annotations.SerializedName


data class RestaurantDetailData (
    val name: String,
    val  rating: Double,
    @SerializedName("image_url") val imageUrl : String,
    @SerializedName("display_phone") val phone : String,
    @SerializedName("categories")val category:List<DetailCategory> ,
    val location : Address,
    val coordinates: MapLocation,
    @SerializedName("price") val priceDetail :String,
    @SerializedName("review_count") val reviewCount :Int,
    @SerializedName("hours")val hours : List<Hours>?,
    @SerializedName("url") val url : String
    )


data class DetailCategory(
    @SerializedName("alias") val alias: String,
    @SerializedName("title") val title: String
     )
data class Address(
    @SerializedName("address1") val address : String,
    @SerializedName("zip_code") val zipCode : Int,
    @SerializedName("city") val city : String
    )
data class MapLocation(
    @SerializedName("latitude") val latitude : Double,
    @SerializedName("longitude") val longitude : Double
)
data class Hours(
    @SerializedName("open") val open:List<Open>,
    @SerializedName("is_open_now") val opeNow:Boolean?,

    )
data class Open(
    @SerializedName("start") val start :Int,
    @SerializedName("end") var end :Int,
    @SerializedName("day") val day :Int,

    )

