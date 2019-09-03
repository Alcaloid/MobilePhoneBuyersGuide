package com.codemobile.mobilephonebuyersguide.fragment.favorite

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import com.codemobile.mobilephonebuyersguide.activity.detail.DetailActivity
import com.codemobile.mobilephonebuyersguide.constantclass.INFORMATION
import com.codemobile.mobilephonebuyersguide.constantclass.PRICE_HIGHTOLOW
import com.codemobile.mobilephonebuyersguide.constantclass.PRICE_LOWTOHIGH
import com.codemobile.mobilephonebuyersguide.constantclass.RATE_5_1
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse
import com.squareup.picasso.Picasso

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

    override fun gotoDetailPage(context: Context, infomation: MobileListResponse) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(INFORMATION, infomation)
        context.startActivity(intent)
    }

    override fun setImageTarget(context: Context, target: ImageView, url: String) {
        Picasso.with(context).load(url).into(target)
    }

    override fun setMobileFav(list: ArrayList<MobileListResponse>?) {
        if (list!=null){
            _view.showMobileFav(list)
        }
    }

}