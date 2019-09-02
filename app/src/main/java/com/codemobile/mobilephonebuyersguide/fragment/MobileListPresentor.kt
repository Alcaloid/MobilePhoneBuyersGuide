package com.codemobile.mobilephonebuyersguide.fragment

import com.codemobile.mobilephonebuyersguide.internet.ApiInterface
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MobileListPresentor (val _view:MobileListContract.View):MobileListContract.Presentor{

    override fun feedMobile() {
        val callMobileList = ApiInterface.getBase().getMobileList()
        callMobileList.enqueue(object : Callback<List<MobileListResponse>> {
            override fun onFailure(call: Call<List<MobileListResponse>>, t: Throwable) {
            }
            override fun onResponse(call: Call<List<MobileListResponse>>, response: Response<List<MobileListResponse>>) {
                if (response.isSuccessful){
                    _view.showMobileList(response.body()!!)
                }
            }
        })
    }

}