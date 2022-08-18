package com.glimiafernandez.yelpProject.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class RestaurantViewModel(private val restaurantsDAO: RestaurantsDAO):ViewModel() {

}
class RestaurantViewModelFactory(private val restaurantsDAO :RestaurantsDAO):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RestaurantViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RestaurantViewModel(restaurantsDAO) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}