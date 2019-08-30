package com.codemobile.mobilephonebuyersguide.nonmvp.internet

import com.codemobile.mobilephonebuyersguide.nonmvp.model.ImageResponse
import com.codemobile.mobilephonebuyersguide.nonmvp.model.MobileListResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("api/mobiles")
    fun getMobileList():
            Call<List<MobileListResponse>>

    @GET("api/mobiles/{id}/images")
    fun getMobileImage(@Path("id")id:String):
            Call<List<ImageResponse>>

    companion object Factory {
        private val BASE_URL = "https://scb-test-mobile.herokuapp.com/"

        private var retrofit: Retrofit? = null

        fun getBase(): ApiInterface {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!.create(ApiInterface::class.java)
        }
    }
}