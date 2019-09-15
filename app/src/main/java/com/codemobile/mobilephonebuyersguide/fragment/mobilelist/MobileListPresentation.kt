package com.codemobile.mobilephonebuyersguide.fragment.mobilelist

import android.content.Context
import com.codemobile.mobilephonebuyersguide.constantclass.*
import com.codemobile.mobilephonebuyersguide.internet.ApiInterface
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.codemobile.mobilephonebuyersguide.database.AppDatabase
import com.codemobile.mobilephonebuyersguide.database.CMWorkerThread

class MobileListPresentation(val _view: MobileListContract.MobileListView, val service: ApiInterface = ApiInterface.getBase()) :
    MobileListContract.MobileListPresenter {

    private var favMobileArrayList: ArrayList<MobileListResponse> = arrayListOf()
    private var mobileArrayList: ArrayList<MobileListResponse> = arrayListOf()
    private var stateTypeSort:String? = null
    private var appDatabase: AppDatabase? = null
    var mCMWorkerThread: CMWorkerThread = CMWorkerThread("favoritedatabase").also {
        it.start()
    }

    override fun sortMobile(sortForm: String) {
        stateTypeSort = sortForm
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
        _view.showMobileList(mobileArrayList)
    }

    override fun feedMobileList() {
        _view.showLoading()
        val call = service.getMobileList()
        call.enqueue(object : Callback<List<MobileListResponse>> {
            override fun onFailure(call: Call<List<MobileListResponse>>, t: Throwable) {
                _view.hideLoading()
                _view.closeRefresh()
                _view.showErrorMessage()
            }

            override fun onResponse(
                call: Call<List<MobileListResponse>>,
                response: Response<List<MobileListResponse>>
            ) {
                if (response.isSuccessful) {
                    mobileArrayList.clear()
                    mobileArrayList.addAll(response.body()!!)
                    checkSort()
                    _view.hideLoading()
                    _view.showMobileList(mobileArrayList)
                    _view.setPreFavorite()
                }
                _view.closeRefresh()
            }
        })
    }

    fun checkSort(){
        stateTypeSort?.let { sortMobile(it) }
    }

    override fun getCurrentFav(list: ArrayList<MobileListResponse>?) {
        mobileArrayList.forEach { item ->
            item.fav = false
        }
        list?.forEach { mobileFav ->
            val favPosition = mobileArrayList.find { mobile ->
                mobile.id == mobileFav.id
            }
            favPosition?.fav = true
        }
        _view.showMobileList(mobileArrayList)
    }

    override fun addFavoriteMobile(target: MobileListResponse) {
        favMobileArrayList.add(target)
    }

    override fun removeFavoriteMobile(target: MobileListResponse) {
        target.fav = true //remove must same object
        favMobileArrayList.remove(target)
    }

    override fun makeFavoriteMobileInRoomDatabase(target: MobileListResponse, roomFunction: String) {
        dataFromRoomDatabase(roomFunction, target)
    }

    override fun getFavoriteMobile(): ArrayList<MobileListResponse> {
        return favMobileArrayList
    }

    override fun setupDatabase(context: Context) {
        appDatabase = AppDatabase.getInstance(context).also {
            it.openHelper.readableDatabase
        }
    }

    override fun checkPreviousFavorite() {
        dataFromRoomDatabase(QUERY_ALLFAV, null)
    }

    private fun dataFromRoomDatabase(stateFunction: String, databaseEntity: MobileListResponse?) {
        val task = Runnable {
            when (stateFunction) {
                QUERY_ALLFAV -> {
                    val result = appDatabase?.favoriteDao()?.queryFavorites()
                    if (result != null) {
                        favMobileArrayList.addAll(result)
                    }
                }
                DELETE_FAV -> {
                    appDatabase?.favoriteDao()?.deleteFavorite(
                        databaseEntity!!
                    )
                }
                ADD_FAV -> {
                    appDatabase?.favoriteDao()?.addFavorite(
                        databaseEntity!!
                    )
                }
            }
        }
        mCMWorkerThread.postTask(task)
    }
}