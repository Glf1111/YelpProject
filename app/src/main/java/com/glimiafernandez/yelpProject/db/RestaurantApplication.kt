package com.glimiafernandez.yelpProject.db

import android.app.Application

class RestaurantApplication: Application() {
    val database: RestaurantDataBase by lazy { RestaurantDataBase.getDatabase(this) }
}