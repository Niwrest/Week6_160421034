package com.ubayadef.advweek6_160421034.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubayadef.advweek6_160421034.model.Hotel

class HotelListAdapter(val hotelList:ArrayList<Hotel>)
    : RecyclerView.Adapter<HotelListAdapter.HotelViewHolder>() {
    class HotelViewHolder(var binding: HotelListAdapter)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        var binding =
            HotelListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HotelViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return hotelList.size
    }
    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        holder.binding.txtNamaHotel.text = hotelList[position].name
        holder.binding.txtLokasi.text = hotelList[position].id
        holder.binding.btnDetail.setOnClickListener {

        }
    }
    fun updateFilmList(newFilmList: ArrayList<Hotel>){
        hotelList.clear()
        hotelList.addAll(newFilmList)
        notifyDataSetChanged()

        // Log to check if the film list is updated
        Log.d("HotelListAdapter", "Hotel list updated: $hotelList")
    }
}