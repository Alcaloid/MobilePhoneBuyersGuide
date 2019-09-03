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


class MobileListPresentation(val _view: MobileListContract.MobileListView) :
    MobileListContract.MobileListPresentor {

    private var favMobileArrayList:ArrayList<MobileListResponse> = arrayListOf()
    private var appDatabase:AppDatabase? = null
    var mCMWorkerThread: CMWorkerThread? = null

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
        //list is item of fav
        mobileArrayList.forEach { item ->
            //very slow!
            item.fav = false
            list?.forEach { itemFav ->
                if (item.id == itemFav.id) {
                    item.fav = true
                }
            }
        }
        _view.showMobileList(mobileArrayList)
    }

    override fun addFavoriteMobile(target: MobileListResponse) {
        favMobileArrayList.add(target)
        val task = Runnable {
            appDatabase?.favoriteDao()?.addFavorite(DatabaseEntity(target.id,target.name
                ,target.description,target.brand,target.price,target.rating,target.thumbImageURL,target.fav))
        }
        mCMWorkerThread?.postTask(task)
    }

    override fun removeFavoriteMobile(target: MobileListResponse) {
        favMobileArrayList.remove(target)
        val task = Runnable {
            appDatabase?.favoriteDao()?.deleteFavorite(DatabaseEntity(target.id,target.name
                ,target.description,target.brand,target.price,target.rating,target.thumbImageURL,target.fav))
        }
        mCMWorkerThread?.postTask(task)
    }

    override fun getFavoriteMobile(): ArrayList<MobileListResponse> {
        return favMobileArrayList
    }

    override fun setupDatabase(context: Context) {
        mCMWorkerThread = CMWorkerThread("favoritedatabase").also {
            it.start()
        }
        appDatabase = AppDatabase.getInstance(context).also {
            it.openHelper.readableDatabase
        }
    }

    override fun checkPreviousFavorite(list: ArrayList<MobileListResponse>) {
        val task = Runnable {
            val result = appDatabase?.favoriteDao()?.queryFavorites()
            println("Result" + result)
            println("List"+list)
        }
        mCMWorkerThread?.postTask(task)
    }


}