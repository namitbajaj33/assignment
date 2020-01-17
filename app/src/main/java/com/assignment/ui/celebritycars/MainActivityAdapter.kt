package com.assignment.ui.celebritycars

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.R
import com.assignment.model.CelebrityCarContainer
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_car.view.*
import kotlinx.android.synthetic.main.item_header.view.*

class MainActivityAdapter(val data: ArrayList<CelebrityCarContainer>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == CelebrityCarContainer.HEADER) {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
            return HeaderHolder(v)
        } else if (viewType == CelebrityCarContainer.CAR) {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
            return CarHolder(v)
        } else {
            // If required we'll use different UI for celebrities here this else is just an example

            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
            return CarHolder(v)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderHolder) {
            holder.bind(data.get(position))
        } else if (holder is CarHolder) {
            holder.bind(data.get(position))
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].viewType
    }

    class HeaderHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(celebrityCarContainer: CelebrityCarContainer) {
            itemView.header.text = celebrityCarContainer.name
        }
    }

    /*class CelebrityHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(celebrityCarContainer: CelebrityCarContainer) {
            //we can use this if we want to show different view for celebrity
        }
    }*/


    class CarHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(celebrityCarContainer: CelebrityCarContainer) {
            Glide.with(itemView.context).load(celebrityCarContainer.celebrityCar.photo)
                .into(itemView.image_view);
            itemView.name.text = celebrityCarContainer.name
        }
    }
}