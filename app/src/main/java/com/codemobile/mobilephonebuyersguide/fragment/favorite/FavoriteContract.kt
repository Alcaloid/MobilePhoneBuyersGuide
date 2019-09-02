package com.codemobile.mobilephonebuyersguide.fragment.favorite

import com.codemobile.mobilephonebuyersguide.model.MobileListResponse

interface FavoriteContract {
    interface favView {
        fun showMobileFav(mobileFav: ArrayList<MobileListResponse>)
    }

    interface favPresentor {
        fun removeMobileFav(mobileFav: ArrayList<MobileListResponse>, position: Int)
        fun sortMobile(mobileArrayList: ArrayList<MobileListResponse>, sortForm: String)
    }
}