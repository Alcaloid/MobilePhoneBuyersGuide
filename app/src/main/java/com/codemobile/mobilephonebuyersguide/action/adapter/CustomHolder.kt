package com.codemobile.mobilephonebuyersguide.app.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.custom_mobile_list_item.view.*

class CustomHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name: TextView = view.txt_header
    val favorite: ImageView = view.image_favorite
    val description: TextView = view.txt_description
    val image_mobile: ImageView = view.image_mobile
    val rate: TextView = view.txt_rating
    val price: TextView = view.txt_price
}