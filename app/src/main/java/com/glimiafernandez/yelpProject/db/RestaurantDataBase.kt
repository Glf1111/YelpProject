package com.glimiafernandez.yelpProject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.glimiafernandez.yelpProject.yelpModels.RestaurantDetailData

@Database(entities = [RestaurantDetailData::class], version = 1)
 abstract class RestaurantDataBase : RoomDatabase() {
    abstract fun localRestaurantDao() : RestaurantsDAO

    companion object {
        @Volatile
        private var INSTANCE: RestaurantDataBase? = null
        fun getDatabase(context: Context): RestaurantDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RestaurantDataBase::class.java,
                    "item_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

 }