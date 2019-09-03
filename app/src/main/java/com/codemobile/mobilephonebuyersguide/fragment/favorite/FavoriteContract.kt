package com.codemobile.mobilephonebuyersguide.fragment.favorite

import android.content.Context
import android.widget.ImageView
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse

interface FavoriteContract {
    interface favView {
        fun showMobileFav(mobileFav: ArrayList<MobileListResponse>)
    }

    interface favPresentor {
        fun removeMobileFav(mobileFav: ArrayList<MobileListResponse>, position: Int)
        fun sortMobile(mobileArrayList: ArrayList<MobileListResponse>, sortForm: String)
        fun setMobileFav(list: ArrayList<MobileListResponse>?)
        fun gotoDetailPage(context: Context, infomation:MobileListResponse)
        fun setImageTarget(context: Context, target: ImageView, url:String)
    }
}