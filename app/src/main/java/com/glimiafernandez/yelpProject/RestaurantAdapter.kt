package com.glimiafernandez.yelpProject

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


class RestaurantAdapter( private val context: Context, private val restaurants: List<YelpRestaurant>):RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_restaurant,parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.bind(restaurant)

    }

    override fun getItemCount() = restaurants.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName = itemView.findViewById<TextView>(R.id.tvName)
        private val ratingBar = itemView.findViewById<RatingBar>(R.id.ratingBar)
        private val tvLocation = itemView.findViewById<TextView>(R.id.tvLocation)
        private val tvCategories = itemView.findViewById<TextView>(R.id.tvCategories)
        private val tvNumberViews = itemView.findViewById<TextView>(R.id.tvNumberViews)
        private val tvDistance = itemView.findViewById<TextView>(R.id.tvDistance)
        private val tvCost = itemView.findViewById<TextView>(R.id.tvCost)
        private val imageView = itemView.findViewById<ImageView>(R.id.imageView)



        fun bind(restaurant: YelpRestaurant) {
            tvName.text = restaurant.name
            ratingBar.rating =restaurant.rating.toFloat()
            tvLocation.text=restaurant.location.address
            tvDistance.text= restaurant.displayDistance()
            tvCost.text= restaurant.price
            tvNumberViews.text = "${restaurant.reviewCount} Reviews"
            tvCategories.text =restaurant.category[0].title
            Glide.with(context).load(restaurant.imageUrl).apply(RequestOptions().transform(CenterCrop(),RoundedCorners(20))).into(imageView)




        }
    }
}


/*
* tvNumberViews.text = "${restaurant.reviewCount}Reviews"

            tvCategories.text =restaurant.category[0].title


            * */
