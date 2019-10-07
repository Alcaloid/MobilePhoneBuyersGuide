package com.codemobile.mobilephonebuyersguide.action.fragment.favorite

import android.content.Context
import com.codemobile.mobilephonebuyersguide.action.model.MobileListResponse

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