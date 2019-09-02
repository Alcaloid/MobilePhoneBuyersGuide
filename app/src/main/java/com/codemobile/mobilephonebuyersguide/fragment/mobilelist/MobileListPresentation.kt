package com.codemobile.mobilephonebuyersguide.fragment.mobilelist

import com.codemobile.mobilephonebuyersguide.constantclass.PRICE_HIGHTOLOW
import com.codemobile.mobilephonebuyersguide.constantclass.PRICE_LOWTOHIGH
import com.codemobile.mobilephonebuyersguide.constantclass.RATE_5_1
import com.codemobile.mobilephonebuyersguide.internet.ApiInterface
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MobileListPresentation(val _view: MobileListContract.MobileListView) :
    MobileListContract.MobileListPresentor {

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

    override fun getCurrentFav(mobileArrayList: ArrayList<MobileListResponse>, list: ArrayList<MobileListResponse>) {
        //list is item of fav
        mobileArrayList.forEach { item ->
            //very slow!
            item.fav = false
            list.forEach { itemFav ->
                if (item.id == itemFav.id) {
                    item.fav = true
                }
            }
        }
        _view.showMobileList(mobileArrayList)
    }

}