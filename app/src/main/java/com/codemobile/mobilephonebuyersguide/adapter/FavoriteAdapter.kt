package com.codemobile.mobilephonebuyersguide.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codemobile.mobilephonebuyersguide.DetailActivity
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse
import com.squareup.picasso.Picasso


class FavoriteAdapter (val context: Context, val mDataArray:ArrayList<MobileListResponse>): RecyclerView.Adapter<CustomHodler>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHodler {
        return CustomHodler(
            LayoutInflater.from(parent.context).inflate(
                com.codemobile.mobilephonebuyersguide.R.layout.custom_mobile_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return mDataArray.size
    }

    override fun onBindViewHolder(holder: CustomHodler, position: Int) {
        holder.name.text            = mDataArray[position].name
        holder.description.text     = mDataArray[position].price.toString()
        holder.price.text           = mDataArray[position].rating.toString()
        holder.price.alpha          = 0.5f
        Picasso.with(context)
            .load(mDataArray[position].thumbImageURL)
            .into(holder.img_mobile)
        holder.rate.visibility      = View.GONE
        holder.favorite.visibility  = View.GONE

        holder.itemView.setOnClickListener {
            val adapterPos = holder.adapterPosition
            if (adapterPos != RecyclerView.NO_POSITION) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("information",mDataArray[position])
                context.startActivity(intent)
            }
        }
    }

}