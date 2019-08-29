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
import com.codemobile.mobilephonebuyersguide.ui.CustomItemTouchHelperListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_mobile_list_item.view.*
import java.util.*
import kotlin.collections.ArrayList


class MobileListAdapter (val context: Context,val setHoler:Int): RecyclerView.Adapter<CustomHodler>(),CustomItemTouchHelperListener{


    private var mDataArray:ArrayList<MobileListResponse> = arrayListOf()
    private var favDataArray:ArrayList<MobileListResponse> = arrayListOf()

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
        if (setHoler == 0){
            setMobileListHoler(holder,position)
        }else{
            setFavoriteHolder(holder,position)
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

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(mDataArray, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
        mDataArray.removeAt(position)
        notifyDataSetChanged()
    }

    fun sublitList(list: ArrayList<MobileListResponse>){
        mDataArray = list
        notifyDataSetChanged()
    }

    fun getFavList():ArrayList<MobileListResponse>{
        return favDataArray
    }

    fun setMobileListHoler(holder: CustomHodler, position: Int){
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
                val indexTarget = favDataArray.find {
                    it.equals(mDataArray[position])
                }
                favDataArray.remove(indexTarget)
                println("Delte_"+favDataArray.size)
                like = false
            }else{
                holder.favorite.setImageResource(R.drawable.ic_favorite)
                favDataArray.add(mDataArray[position])
                println("Add_"+favDataArray.size)
                like = true
            }
        }
    }

    fun setFavoriteHolder(holder: CustomHodler, position: Int){
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


