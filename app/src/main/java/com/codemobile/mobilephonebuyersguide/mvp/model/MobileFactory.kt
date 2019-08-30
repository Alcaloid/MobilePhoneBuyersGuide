package com.codemobile.mobilephonebuyersguide.mvp.model

import com.codemobile.mobilephonebuyersguide.mvp.constantclass.BASE_URL
import com.codemobile.mobilephonebuyersguide.nonmvp.internet.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MobileFactory {
    companion object Factory{
        private var retrofit: Retrofit? = null
        fun getBase(): MobileApi {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!.create(MobileApi::class.java)
        }
    }
}