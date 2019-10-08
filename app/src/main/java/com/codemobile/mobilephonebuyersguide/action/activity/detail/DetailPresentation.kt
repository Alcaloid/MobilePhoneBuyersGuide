package com.codemobile.mobilephonebuyersguide.action.activity.detail

import com.codemobile.mobilephonebuyersguide.action.center.MessageFunction
import com.codemobile.mobilephonebuyersguide.action.center.MyDagger
import com.codemobile.mobilephonebuyersguide.app.activity.detail.DetailContract
import com.codemobile.mobilephonebuyersguide.action.internet.ApiInterface
import com.codemobile.mobilephonebuyersguide.action.model.ImageResponse
import com.codemobile.mobilephonebuyersguide.action.model.MobileListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DetailPresentation @Inject constructor(val messageFunction: MessageFunction) :
    DetailContract.DetailPresentation {

    lateinit var view: DetailContract.DetailView
    val service: ApiInterface = ApiInterface.getBase()

    fun setView(dataContext:DetailActivity){
        this.view = dataContext
    }
    override fun getPassData(info: MobileListResponse) {
        view.setName(info.name)
        view.setBrand(info.brand)
        view.setDescription(info.description)
        view.setPrice(info.price.toString())
        view.setRate(info.rating.toString())
        println(messageFunction.getMessage())
    }

    override fun feedImageDetail(id: Int) {
        val call = service.getMobileImage(id.toString())
        call.enqueue(object : Callback<List<ImageResponse>> {
            override fun onFailure(call: Call<List<ImageResponse>>, t: Throwable) {
                view.showErrorMessage()
            }

            override fun onResponse(call: Call<List<ImageResponse>>, response: Response<List<ImageResponse>>) {
                if (response.isSuccessful) {
                    response.body().let { data ->
                        if (data != null) {
                            view.showImageMobileList(data)
                        }
                    }
                }else{
                    view.showErrorMessage()
                }
            }
        })
    }
}