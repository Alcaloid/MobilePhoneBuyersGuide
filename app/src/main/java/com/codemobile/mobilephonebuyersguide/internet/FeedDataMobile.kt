package com.codemobile.mobilephonebuyersguide.internet

import androidx.print.PrintHelper
import com.codemobile.mobilephonebuyersguide.model.ImageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedDataMobile {

    fun feedImageMobile(id: String):ArrayList<ImageResponse>{
        val call = ApiInterface.getBase().getMobileImage(id)
        val dataArrayList:ArrayList<ImageResponse> = arrayListOf()
        //check request
        call.enqueue(object : Callback<List<ImageResponse>> {
            override fun onFailure(call: Call<List<ImageResponse>>, t: Throwable) {
                println("Fail")
            }
            override fun onResponse(call: Call<List<ImageResponse>>, response: Response<List<ImageResponse>>) {
                if (response.isSuccessful){
                    dataArrayList.addAll(response.body()!!)
                }
            }
        })
        return dataArrayList
    }
}