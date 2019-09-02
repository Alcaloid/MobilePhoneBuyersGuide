package com.codemobile.mobilephonebuyersguide.fragment.favorite

import com.codemobile.mobilephonebuyersguide.constantclass.PRICE_HIGHTOLOW
import com.codemobile.mobilephonebuyersguide.constantclass.PRICE_LOWTOHIGH
import com.codemobile.mobilephonebuyersguide.constantclass.RATE_5_1
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse

class FavoritePresentation(val _view: FavoriteContract.favView) :
    FavoriteContract.favPresentor {
    override fun removeMobileFav(mobileFav: ArrayList<MobileListResponse>, position: Int) {
        mobileFav.removeAt(position)
        _view.showMobileFav(mobileFav)
    }

    override fun sortMobile(mobileArrayList: ArrayList<MobileListResponse>, sortForm: String) {
        when (sortForm) {
            PRICE_LOWTOHIGH -> {
                mobileArrayList.sortBy { it.price }
            }
            PRICE_HIGHTOLOW -> {
                mobileArrayList.sortByDescending { it.price }
            }
            RATE_5_1 -> {
                mobileArrayList.sortByDescending { it.rating }
            }
            else -> {
                mobileArrayList.sortBy { it.price }
            }
        }
        _view.showMobileFav(mobileArrayList)
    }

}