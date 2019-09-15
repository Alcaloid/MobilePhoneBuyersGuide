package com.codemobile.mobilephonebuyersguide.fragment.favorite

import android.content.Context
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse

interface FavoriteContract {
    interface FavoriteView {
        fun showMobileFav(mobileFav: ArrayList<MobileListResponse>)
    }

    interface FavoritePresenter {
        fun removeMobileFav(position: Int)
        fun deleteMobileFavInDatabase(position: Int)
        fun sortMobile(sortForm: String)
        fun setMobileFav(list: ArrayList<MobileListResponse>?)
        fun initDatabase(context: Context)
        fun getMobileFavorite(): ArrayList<MobileListResponse>
    }
}