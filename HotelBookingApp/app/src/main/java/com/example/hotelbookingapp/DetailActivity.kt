package com.example.hotelbookingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.hotelbookingapp.database.Hotel

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val hotel = intent.getSerializableExtra("hotel") as? Hotel

        val hotelImage = findViewById<ImageView>(R.id.detailHotelImage)
        val hotelName = findViewById<TextView>(R.id.detailHotelName)
        val hotelLocation = findViewById<TextView>(R.id.detailHotelLocation)
        val hotelPrice = findViewById<TextView>(R.id.detailHotelPrice)
        val hotelRating = findViewById<TextView>(R.id.detailHotelRating)
        val bookButton = findViewById<Button>(R.id.bookButton)
        val hotelFeatures = findViewById<TextView>(R.id.detailHotelFeatures)

        hotel?.let {
            hotelImage.setImageResource(it.imageResId)
            hotelName.text = it.name
            hotelLocation.text = "${it.city}, ${it.district}"
            hotelPrice.text = "Fiyat: ${it.price}$"
            hotelRating.text = "Puan: ${it.rating}"
            hotelFeatures.text = "Açıklama: ${it.features}"
        }

        // ⭐️ Buraya Intent ekliyoruz:
        bookButton.setOnClickListener {
            val intent = Intent(this, ReservationActivity::class.java)
            // Eğer otel bilgilerini reservation sayfasına da göndermek istersen:
            intent.putExtra("hotel", hotel) // Hotel sınıfın Serializable olmalı.
            startActivity(intent)
        }
    }
}
