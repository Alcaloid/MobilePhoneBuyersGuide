package com.codemobile.mobilephonebuyersguide.activity.detail

import com.codemobile.mobilephonebuyersguide.internet.ApiInterface
import com.codemobile.mobilephonebuyersguide.model.ImageResponse
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresentation(val view: DetailContract.DetailView) :
    DetailContract.DetailPresenttaion {

    override fun getPassData(info: MobileListResponse) {
        view.setName(info.name)
        view.setBrand(info.brand)
        view.setDescription(info.description)
        view.setPrice(info.price.toString())
        view.setRate(info.rating.toString())
    }

    override fun feedImageDetail(id: Int) {
        val callImageMobile = ApiInterface.getBase().getMobileImage(id.toString())
        callImageMobile.enqueue(object : Callback<List<ImageResponse>> {
            override fun onFailure(call: Call<List<ImageResponse>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<ImageResponse>>, response: Response<List<ImageResponse>>) {
                if (response.isSuccessful) {
                    val detailImage: ArrayList<ImageResponse> = arrayListOf()
                    detailImage.addAll(response.body()!!)
                    view.showImageMobileList(detailImage)
                }
            }
        })
    }

}