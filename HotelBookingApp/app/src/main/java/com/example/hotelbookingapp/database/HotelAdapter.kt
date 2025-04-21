package com.example.hotelbookingapp.database

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelbookingapp.DetailActivity
import com.example.hotelbookingapp.R

class HotelAdapter(private val hotels: List<Hotel>) : RecyclerView.Adapter<HotelAdapter.HotelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hotel_item, parent, false)
        return HotelViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        val hotel = hotels[position]
        holder.itemView.findViewById<TextView>(R.id.hotelName).text = hotel.name
        holder.itemView.findViewById<TextView>(R.id.hotelCity).text = "${hotel.city}, ${hotel.district}"
        holder.itemView.findViewById<TextView>(R.id.hotelPrice).text = "${hotel.price}$"
        holder.itemView.findViewById<TextView>(R.id.hotelRating).text = "Rating: ${hotel.rating}"
        holder.itemView.findViewById<ImageView>(R.id.hotelImage).setImageResource(hotel.imageResId)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("hotel", hotel)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = hotels.size

    class HotelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // ItemView üzerinden öğelere erişim sağlanıyor
    }
}


