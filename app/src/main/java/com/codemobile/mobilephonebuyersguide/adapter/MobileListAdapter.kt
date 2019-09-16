package com.codemobile.mobilephonebuyersguide.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codemobile.mobilephonebuyersguide.R
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList


class MobileListAdapter(val context: Context, private val setHolder: Int, private val mobileAdapterInterface: MobileAdapterInterface) :
    RecyclerView.Adapter<CustomHolder>() {

    private var mDataArray: ArrayList<MobileListResponse> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        return CustomHolder(
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

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
        if (setHolder == 0) {
            setMobileListHolder(holder, position)
        } else {
            setFavoriteHolder(holder, position)
        }
        holder.itemView.setOnClickListener {
            val adapterPos = holder.adapterPosition
            if (adapterPos != RecyclerView.NO_POSITION) {
                mobileAdapterInterface.gotoDetailPage(mDataArray[position])
            }
        }
    }

    interface MobileAdapterInterface {
        fun gotoDetailPage(infomation: MobileListResponse)
        fun addFavMobile(target: MobileListResponse)
        fun removeFavMobile(target: MobileListResponse)
    }

    fun submitList(list: ArrayList<MobileListResponse>) {
        mDataArray = list
        notifyDataSetChanged()
    }

    private fun setMobileListHolder(holder: CustomHolder, position: Int) {
        var like: Boolean = mDataArray[position].fav
        holder.name.text = mDataArray[position].name
        holder.description.text = mDataArray[position].description
        holder.price.text = mDataArray[position].price.toString()
        holder.rate.text = mDataArray[position].rating.toString()
        Picasso.with(context).load(mDataArray[position].thumbImageURL).into(holder.image_mobile)

        //when sort image fav must change
        if (like) {
            holder.favorite.setImageResource(R.drawable.ic_favorite)
        } else {
            holder.favorite.setImageResource(R.drawable.ic_heart)
        }

        holder.favorite.setOnClickListener {
            //switch fav or not
            if (like) {
                holder.favorite.setImageResource(R.drawable.ic_heart)
                mDataArray[position].fav = false
                mobileAdapterInterface.removeFavMobile(mDataArray[position])
                like = false
            } else {
                holder.favorite.setImageResource(R.drawable.ic_favorite)
                mDataArray[position].fav = true
                mobileAdapterInterface.addFavMobile(mDataArray[position])
                like = true
            }
        }
    }

    private fun setFavoriteHolder(holder: CustomHolder, position: Int) {
        holder.name.text = mDataArray[position].name
        holder.description.text = mDataArray[position].price.toString()
        holder.price.text = mDataArray[position].rating.toString()
        holder.price.alpha = 0.5f
        Picasso.with(context).load(mDataArray[position].thumbImageURL).into(holder.image_mobile)
        holder.rate.visibility = View.GONE
        holder.favorite.visibility = View.GONE
    }

}


