package com.codemobile.mobilephonebuyersguide.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codemobile.mobilephonebuyersguide.R
import com.codemobile.mobilephonebuyersguide.model.ImageResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.grid_item_mobile_image.view.*

class ImageMobileListAdapter (val context: Context): RecyclerView.Adapter<ImageHolder>(){

    private var urlArray:List<ImageResponse> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        return ImageHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.grid_item_mobile_image,
                parent,
                false
            )
        )
    }

    fun sublitList(list: List<ImageResponse>){
        urlArray = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return urlArray.size
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        Picasso.with(context)
            .load(urlArray[position].url)
            .into(holder.imageMobile)
    }
}

class ImageHolder(view: View): RecyclerView.ViewHolder(view) {
    val imageMobile = view.grid_item
}