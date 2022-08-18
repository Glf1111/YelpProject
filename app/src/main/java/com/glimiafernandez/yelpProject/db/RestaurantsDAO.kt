package com.glimiafernandez.yelpProject.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.glimiafernandez.yelpProject.yelpModels.RestaurantDetailData

@Dao
interface RestaurantsDAO {

        @Query("SELECT * FROM favPlaces")
        suspend fun getAllDetails() : LiveData<List<RestaurantDetailData>>

        @Insert(onConflict = REPLACE)
        suspend fun insertAll(restaurantDetailData : RestaurantDetailData)

        @Delete
        suspend fun delete(restaurantDetailData: RestaurantDetailData)


}