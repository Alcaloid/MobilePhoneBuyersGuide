package com.codemobile.mobilephonebuyersguide.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codemobile.mobilephonebuyersguide.DetailActivity
import com.codemobile.mobilephonebuyersguide.R
import com.codemobile.mobilephonebuyersguide.fragment.FavouriteFragment
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_mobile_list_item.view.*


class MobileListAdapter (val context: Context, val mDataArray:ArrayList<MobileListResponse>): RecyclerView.Adapter<CustomHodler>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHodler {
        return CustomHodler(
            LayoutInflater.from(parent.context).inflate(
                R.layout.custom_mobile_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return mDataArray.size
    }


    override fun onBindViewHolder(holder: CustomHodler, position: Int) {
        var like:Boolean = false
        holder.name.text = mDataArray[position].name
        holder.description.text = mDataArray[position].description
        holder.price.text = mDataArray[position].price.toString()
        holder.rate.text = mDataArray[position].rating.toString()
        Picasso.with(context)
            .load(mDataArray[position].thumbImageURL)
            .into(holder.img_mobile)
        holder.favorite.setOnClickListener {
            if (like){
                holder.favorite.setImageResource(R.drawable.ic_heart)
                val indexTarget = FavouriteFragment.favoriteItem.find {
                    it.equals(mDataArray[position])
                }
                FavouriteFragment.favoriteItem.remove(indexTarget)
                like = false
            }else{
                holder.favorite.setImageResource(R.drawable.ic_favorite)
                FavouriteFragment.favoriteItem.add(mDataArray[position])
                println("Add_"+FavouriteFragment.favoriteItem.size)
                like = true
            }
        }

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


