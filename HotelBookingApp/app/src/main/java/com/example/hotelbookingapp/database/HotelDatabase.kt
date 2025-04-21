package com.example.hotelbookingapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Hotel::class], version = 1)
abstract class HotelDatabase : RoomDatabase() {
    abstract fun hotelDao(): HotelDao
}