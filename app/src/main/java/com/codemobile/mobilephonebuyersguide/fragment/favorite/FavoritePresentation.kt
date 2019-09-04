package com.codemobile.mobilephonebuyersguide.fragment.favorite

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import com.codemobile.mobilephonebuyersguide.activity.detail.DetailActivity
import com.codemobile.mobilephonebuyersguide.constantclass.INFORMATION
import com.codemobile.mobilephonebuyersguide.constantclass.PRICE_HIGHTOLOW
import com.codemobile.mobilephonebuyersguide.constantclass.PRICE_LOWTOHIGH
import com.codemobile.mobilephonebuyersguide.constantclass.RATE_5_1
import com.codemobile.mobilephonebuyersguide.database.AppDatabase
import com.codemobile.mobilephonebuyersguide.database.CMWorkerThread
import com.codemobile.mobilephonebuyersguide.database.DatabaseEntity
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse
import com.squareup.picasso.Picasso

class FavoritePresentation(val _view: FavoriteContract.favView) :
    FavoriteContract.favPresentor {

    private var favoriteMobile: ArrayList<MobileListResponse> = arrayListOf()

    var mCMWorkerThread: CMWorkerThread = CMWorkerThread("favoritedatabase").also {
        it.start()
    }
    private var appDatabase: AppDatabase? = null

    override fun removeMobileFav(position: Int) {
        val target = favoriteMobile[position]
        val task = Runnable {
            appDatabase?.favoriteDao()?.deleteFavorite(
                DatabaseEntity(
                    target.id, target.name
                    , target.description, target.brand, target.price, target.rating, target.thumbImageURL, target.fav
                )
            )
        }
        mCMWorkerThread.postTask(task)
        favoriteMobile.removeAt(position)
        _view.showMobileFav(favoriteMobile)
    }

    override fun sortMobile(sortForm: String) {
        when (sortForm) {
            PRICE_LOWTOHIGH -> {
                favoriteMobile.sortBy { it.price }
            }
            PRICE_HIGHTOLOW -> {
                favoriteMobile.sortByDescending { it.price }
            }
            RATE_5_1 -> {
                favoriteMobile.sortByDescending { it.rating }
            }
            else -> {
                favoriteMobile.sortBy { it.price }
            }
        }
        _view.showMobileFav(favoriteMobile)
    }

    override fun initDatabase(context: Context) {
        appDatabase = AppDatabase.getInstance(context).also {
            it.openHelper.readableDatabase
        }
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
        if (list != null) {
            favoriteMobile = list
            _view.showMobileFav(favoriteMobile)
        }
    }

    override fun getMobileFavorite(): ArrayList<MobileListResponse> {
        return favoriteMobile
    }

}