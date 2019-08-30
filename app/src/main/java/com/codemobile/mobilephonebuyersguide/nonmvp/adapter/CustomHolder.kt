package com.codemobile.mobilephonebuyersguide.nonmvp.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.custom_mobile_list_item.view.*

class CustomHodler(view: View): RecyclerView.ViewHolder(view) {
    val name = view.txt_header
    val favorite = view.image_favorite
    val description = view.txt_description
    val img_mobile = view.image_mobile
    val rate = view.txt_rating
    val price = view.txt_price
}