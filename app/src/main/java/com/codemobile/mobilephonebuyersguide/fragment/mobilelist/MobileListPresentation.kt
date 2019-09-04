package com.codemobile.mobilephonebuyersguide.fragment.mobilelist

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import com.codemobile.mobilephonebuyersguide.activity.detail.DetailActivity
import com.codemobile.mobilephonebuyersguide.constantclass.*
import com.codemobile.mobilephonebuyersguide.internet.ApiInterface
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.codemobile.mobilephonebuyersguide.database.AppDatabase
import com.codemobile.mobilephonebuyersguide.database.CMWorkerThread
import com.codemobile.mobilephonebuyersguide.database.DatabaseEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MobileListPresentation(val _view: MobileListContract.MobileListView) :
    MobileListContract.MobileListPresentor {

    private var favMobileArrayList: ArrayList<MobileListResponse> = arrayListOf()
    private var appDatabase: AppDatabase? = null
    val gson = Gson()
    var mCMWorkerThread: CMWorkerThread = CMWorkerThread("favoritedatabase").also {
        it.start()
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
        _view.showMobileList(mobileArrayList)
    }

    override fun feedMobileList() {
        val callMobileList = ApiInterface.getBase().getMobileList()
        _view.showLoading()
        callMobileList.enqueue(object : Callback<List<MobileListResponse>> {
            override fun onFailure(call: Call<List<MobileListResponse>>, t: Throwable) {
                _view.hideLoading()
                _view.showErrorMessage()
            }

            override fun onResponse(
                call: Call<List<MobileListResponse>>,
                response: Response<List<MobileListResponse>>
            ) {
                if (response.isSuccessful) {
                    val mobileArray: ArrayList<MobileListResponse> = arrayListOf()
                    mobileArray.addAll(response.body()!!)
                    _view.hideLoading()
                    _view.showMobileList(mobileArray)
                    _view.setPreFavorite()
                }
            }
        })
    }

    override fun gotoDetailPage(context: Context, infomation: MobileListResponse) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(INFORMATION, infomation)
        context.startActivity(intent)
    }

    override fun setImageTarget(context: Context, target: ImageView, url: String) {
        Picasso.with(context).load(url).into(target)
    }

    override fun getCurrentFav(mobileArrayList: ArrayList<MobileListResponse>, list: ArrayList<MobileListResponse>?) {
        // size mobile -> 10
        // size fav-> 5
        // 1-> run 10 + run 5
        // 2-> run 10*5
        mobileArrayList.forEach { item ->
            item.fav = false
        }
        list?.forEach { mobileFav ->
            val favPosition = mobileArrayList.find { mobile ->
                mobile.id == mobileFav.id
            }
            favPosition?.fav = true
        }
//        //list is item of fav -> size x size
//        mobileArrayList.forEach { item ->
//            //very slow!
//            item.fav = false
//            list?.forEach { itemFav ->
//                if (item.id == itemFav.id) {
//                    item.fav = true
//                }
//            }
//        }
        _view.showMobileList(mobileArrayList)
    }

    override fun addFavoriteMobile(target: MobileListResponse) {
        val data = DatabaseEntity(
            target.id,
            target.name,
            target.description,
            target.brand,
            target.price,
            target.rating,
            target.thumbImageURL,
            target.fav
        )
        dataFromRoomDatabase(ADD_FAV, data)
        favMobileArrayList.add(target)
    }

    override fun removeFavoriteMobile(target: MobileListResponse) {
        val data = DatabaseEntity(
            target.id,
            target.name,
            target.description,
            target.brand,
            target.price,
            target.rating,
            target.thumbImageURL,
            target.fav
        )
        dataFromRoomDatabase(DELETE_FAV, data)
        target.fav = true //remove must same object
        favMobileArrayList.remove(target)

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

    fun dataFromRoomDatabase(stateFunction: String, databaseEntity: DatabaseEntity?) {
        val task = Runnable {
            when (stateFunction) {
                QUERY_ALLFAV -> {
                    val result = appDatabase?.favoriteDao()?.queryFavorites()
                    val json = gson.toJson(result)
                    val data =
                        gson.fromJson<List<MobileListResponse>>(
                            json,
                            object : TypeToken<List<MobileListResponse>>() {}.type
                        )
                    favMobileArrayList.addAll(data)
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