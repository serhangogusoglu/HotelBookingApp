package com.example.hotelbookingapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hotels")
data class Hotel(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    val name: String,
    val city: String,
    val district: String,
    val price: Double,
    val rating: Float,
    val imageResId: Int,
    val features: String
) : java.io.Serializable
