package com.codemobile.mobilephonebuyersguide.fragment.favorite

import android.content.Context
import android.widget.ImageView
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse

interface FavoriteContract {
    interface favView {
        fun showMobileFav(mobileFav: ArrayList<MobileListResponse>)
    }

    interface favPresentor {
        fun removeMobileFav(position: Int)
        fun sortMobile(sortForm: String)
        fun setMobileFav(list: ArrayList<MobileListResponse>?)
        fun initDatabase(context: Context)
        fun getMobileFavorite(): ArrayList<MobileListResponse>
    }
}