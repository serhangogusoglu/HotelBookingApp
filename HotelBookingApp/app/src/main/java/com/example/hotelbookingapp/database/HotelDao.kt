package com.example.hotelbookingapp.database

import androidx.core.view.WindowInsetsCompat.Type.InsetsType
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HotelDao {
    @Insert
     fun insertHotel(hotel: Hotel)

    @Query("SELECT * FROM hotels")
    fun getAllHotels(): LiveData<List<Hotel>>
}