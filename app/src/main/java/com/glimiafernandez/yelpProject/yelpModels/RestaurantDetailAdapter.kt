package com.glimiafernandez.yelpProject.yelpModels

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.icu.util.Calendar
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.glimiafernandez.yelpProject.R
import java.time.DayOfWeek

private const val TAG = "RestaurantDetailAdapter"


class RestaurantDetailAdapter (private val context: Context, private val restaurantDetailData: List<RestaurantDetailData> ):
    RecyclerView.Adapter<RestaurantDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.rv_detail_views, parent, false) as View
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurantDetailData = restaurantDetailData[position]
       Log.i(TAG, "clicked position $position")

        holder.bind(restaurantDetailData)
    }

    override fun getItemCount() = restaurantDetailData.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var reviews = itemView.findViewById<TextView>(R.id.reviewCount)
        private var mapView = itemView.findViewById<ImageView>(R.id.imageMap)
        private var price = itemView.findViewById<TextView>(R.id.detailPrice)
        private var category = itemView.findViewById<TextView>(R.id.detailCategory)
        private var phone = itemView.findViewById<TextView>(R.id.phoneNum)
        private var location = itemView.findViewById<TextView>(R.id.location)
        private var zipcode = itemView.findViewById<TextView>(R.id.zipCode)
        private var status = itemView.findViewById<TextView>(R.id.statusDetail)
        private var ratingBarDetail = itemView.findViewById<RatingBar>(R.id.ratingDetailBar)
        private var url = itemView.findViewById<TextView>(R.id.url)
        private var detailHours = itemView.findViewById<TextView>(R.id.detailHours)
        private var nameDay = itemView.findViewById<TextView>(R.id.nameDay)



        @SuppressLint("NewApi", "SetTextI18n")
        fun bind(restaurantDetailData: RestaurantDetailData) {

            val lat = restaurantDetailData.coordinates.latitude
            val lon = restaurantDetailData.coordinates.longitude
            val alias = restaurantDetailData.category



            alias.forEach {

                category.text = it.alias
            }
            Glide.with(context)
                .load(
                    "https://maps.googleapis.com/maps/api/staticmap?zoom=18&size=2500x2500&scale=2&maptype=roadmap&" +
                            "markers=color:red%7Clabel:Place%7C$lat,$lon&key=AIzaSyCltKuYHFGANHRjghfs3hsspR4aNPlZGXw"
                )
                .into(mapView)

            reviews.text = restaurantDetailData.reviewCount.toString()
            price.text = restaurantDetailData.priceDetail
            phone.text = restaurantDetailData.phone
            location.text = restaurantDetailData.location.address
            zipcode.text = "${restaurantDetailData.location.zipCode},${restaurantDetailData.location.city}"
            ratingBarDetail.rating = restaurantDetailData.rating.toFloat()
            url.text = restaurantDetailData.url

            val calendar = Calendar.getInstance()
            val dayCalendar = calendar.get(Calendar.DAY_OF_WEEK)

            val realDay = dayCalendar -1
            Log.i(TAG,"calendar $dayCalendar")
            val currentDay = DayOfWeek.of(realDay)




            val sta = "Open"
            val sta1 = "Closed"
            val spannableClosed = SpannableString(sta1)
            val spannableOpen = SpannableString(sta)
            val backColorRed = ForegroundColorSpan(Color.RED)
            val backColorBlue = ForegroundColorSpan(Color.BLUE)
            spannableClosed.setSpan(backColorRed,0,sta1.length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannableOpen.setSpan(backColorBlue,0,4,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            if(restaurantDetailData.hours == null) {
                status.text = context.getString(R.string.NO_INFO)

            }
            else
                restaurantDetailData.hours.forEach { Hours ->


                    if (Hours.opeNow == true){
                            status.text = spannableOpen


                        val listFilterOpen =  Hours.open.filter { it.day + 1 == realDay }

                        listFilterOpen.forEach {

                            val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)

                            if (it.end == 0){
                                it.end = 2400
                                }
                                if (( it.end > hourOfDay *100)  || (it.start < hourOfDay * 100 )){
                                    val starEnd = it.end
                                    Log.i(TAG,starEnd.toString())

                                    if(it.end.toString().length < 4 ){
                                        nameDay.text = context.getString(R.string.NEXT_DAY)
                                        detailHours.text = "${it.start.toString().take(2)}:" +
                                                "${it.start.toString().takeLast(2)} - ${it.end.toString().take(1)}:${it.end.toString().takeLast(2)}"
                                    }
                                    else{

                                        nameDay.text = currentDay.toString()
                                        detailHours.text = "${it.start.toString().take(2)}:" +
                                            "${it.start.toString().takeLast(2)} - ${it.end.toString().take(2)}:${it.end.toString().takeLast(2)}"
                                    }
                                }




                        }


                }
                    else {
                        status.text = spannableClosed
                        detailHours.text = "No_Info"
                }

        }


         }
    }
}


















        





