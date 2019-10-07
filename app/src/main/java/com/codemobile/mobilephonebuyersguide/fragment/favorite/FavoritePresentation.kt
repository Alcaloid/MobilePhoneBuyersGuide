package com.codemobile.mobilephonebuyersguide.fragment.favorite

import android.content.Context
import com.codemobile.mobilephonebuyersguide.constantclass.PRICE_HIGHTOLOW
import com.codemobile.mobilephonebuyersguide.constantclass.PRICE_LOWTOHIGH
import com.codemobile.mobilephonebuyersguide.constantclass.RATE_5_1
import com.codemobile.mobilephonebuyersguide.database.AppDatabase
import com.codemobile.mobilephonebuyersguide.database.CMWorkerThread
import com.codemobile.mobilephonebuyersguide.function.MobileFunction
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse

class FavoritePresentation(private val _view: FavoriteContract.FavoriteView) :
    FavoriteContract.FavoritePresenter, MobileFunction() {

    private var favoriteMobile: ArrayList<MobileListResponse> = arrayListOf()
    private var appDatabase: AppDatabase? = null
    private var stateTypeSort: String? = null
    var mCMWorkerThread: CMWorkerThread = CMWorkerThread("favoritedatabase").also {
        it.start()
    }

    override fun removeMobileFav(position: Int) {
        favoriteMobile.removeAt(position)
        _view.showMobileFav(favoriteMobile)
    }

    override fun deleteMobileFavInDatabase(position: Int) {
        val target = favoriteMobile[position]
        val task = Runnable {
            appDatabase?.favoriteDao()?.deleteFavorite(
                target
            )
        }
        mCMWorkerThread.postTask(task)
    }

    override fun sortMobile(sortForm: String) {
        stateTypeSort = sortForm
        favoriteMobile = sortMobile(favoriteMobile,sortForm)
        _view.showMobileFav(favoriteMobile)
    }

    override fun initDatabase(context: Context) {
        appDatabase = AppDatabase.getInstance(context).also {
            it.openHelper.readableDatabase
        }
    }

    override fun setMobileFav(list: ArrayList<MobileListResponse>?) {
        if (list != null) {
            favoriteMobile = list
            _view.showMobileFav(favoriteMobile)
            checkSortType()
        }
    }

    private fun checkSortType() {
        stateTypeSort?.let { sortMobile(it) }
    }

    override fun getMobileFavorite(): ArrayList<MobileListResponse> {
        return favoriteMobile
    }

}