package com.glimiafernandez.yelpProject.yelpModels

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class YelpDataClasses (
    @SerializedName("total") val total: Int,
    @SerializedName("businesses") val restaurants : List<YelpRestaurant>
        ):Serializable
data class YelpRestaurant(
    val name: String,
    val rating: Double,
    @SerializedName("id") val id :String,
    @SerializedName("price") val price :String,
    @SerializedName("review_count") val reviewCount :String,
    @SerializedName("image_url") val imageUrl : String,
    @SerializedName("distance") val distanceInMeters :Double,
    @SerializedName("categories")val category: List<YelpCategory>,

    val location: YelpAddress
    ):Serializable {
    fun displayDistance(): String {
    return "${distanceInMeters.toInt()} m"
}

}



data class YelpCategory(
    val title : String
):Serializable
data class YelpAddress(
    @SerializedName("address1") val address:String
):Serializable