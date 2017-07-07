package com.faizal.bikesmap

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.faizal.bikesmap.Model.BikeInfo

/**
 * Created by fpatel on 21/05/2017.
 */

class Adapter(private val bikeList: List<BikeInfo>) : RecyclerView.Adapter<Adapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.bikeinforow, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = bikeList[position].getName()
        holder.number.text = bikeList[position].getNumber()
        holder.address.text = bikeList[position].getAddress()

    }

    override fun getItemCount(): Int {
        return bikeList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var number: TextView
        var address: TextView

        init {
            name = itemView.findViewById(R.id.name) as TextView
            number = itemView.findViewById(R.id.number) as TextView
            address = itemView.findViewById(R.id.address) as TextView

        }
    }
}
