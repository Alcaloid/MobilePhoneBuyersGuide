package com.codemobile.mobilephonebuyersguide.mvp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MobileApi {
    @GET("api/mobiles")
    fun getMobile():
            Call<List<MobileResponse>>

    @GET("api/mobiles/{id}/images")
    fun getMobileImage(@Path("id")id:String):
            Call<List<MobileImageResponse>>
}