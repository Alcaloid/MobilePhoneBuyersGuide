package com.codemobile.mobilephonebuyersguide.action.activity.detail

import com.codemobile.mobilephonebuyersguide.action.internet.ApiInterface
import com.codemobile.mobilephonebuyersguide.action.model.ImageResponse
import com.codemobile.mobilephonebuyersguide.action.model.MobileListResponse
import com.codemobile.mobilephonebuyersguide.app.activity.detail.DetailContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DetailPresentation @Inject constructor(
    val view: DetailContract.DetailView
) :
    DetailContract.DetailPresentation {

    private val service: ApiInterface = ApiInterface.getBase()

    override fun getPassData(info: MobileListResponse) {
        view.setName(info.name)
        view.setBrand(info.brand)
        view.setDescription(info.description)
        view.setPrice(info.price.toString())
        view.setRate(info.rating.toString())
    }

    override fun feedImageDetail(id: Int) {
        val call = service.getMobileImage(id.toString())
        call.enqueue(object : Callback<List<ImageResponse>> {
            override fun onFailure(call: Call<List<ImageResponse>>, t: Throwable) {
                view.showErrorMessage()
            }

            override fun onResponse(
                call: Call<List<ImageResponse>>,
                response: Response<List<ImageResponse>>
            ) {
                if (response.isSuccessful) {
                    response.body().let { data ->
                        if (data != null) {
                            view.showImageMobileList(data)
                        }
                    }
                } else {
                    view.showErrorMessage()
                }
            }
        })
    }
}