package com.glimiafernandez.yelpProject.yelpModels


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "favPlaces")
data class RestaurantDetailData (
    @PrimaryKey(autoGenerate = true)
    val name: String,
    val  rating: Double,
    @SerializedName("image_url") val imageUrl : String,
    @SerializedName("display_phone") val phone : String,
    @SerializedName("categories")val category:List<DetailCategory>,
    val location : Address,
    val coordinates: MapLocation,
    @SerializedName("price") val priceDetail :String,
    @SerializedName("review_count") val reviewCount :Int,
    @SerializedName("hours")val hours : List<Hours>?,
    @SerializedName("url") val url : String
    ):Serializable


data class DetailCategory(
    @SerializedName("alias") val alias: String,
    @SerializedName("title") val title: String
     ):Serializable
data class Address(
    @SerializedName("address1") val address : String,
    @SerializedName("zip_code") val zipCode : Int,
    @SerializedName("city") val city : String
    ):Serializable
data class MapLocation(
    @SerializedName("latitude") val latitude : Double,
    @SerializedName("longitude") val longitude : Double
):Serializable
data class Hours(
    @SerializedName("open") val open:List<Open>,
    @SerializedName("is_open_now") val opeNow:Boolean?,

    ):Serializable
data class Open(
    @SerializedName("start") val start :Int,
    @SerializedName("end") var end :Int,
    @SerializedName("day") val day :Int,

    ):Serializable

