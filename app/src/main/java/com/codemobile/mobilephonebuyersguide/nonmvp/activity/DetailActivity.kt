package com.codemobile.mobilephonebuyersguide.nonmvp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.codemobile.mobilephonebuyersguide.R
import com.codemobile.mobilephonebuyersguide.nonmvp.adapter.ImageMobileListAdapter
import com.codemobile.mobilephonebuyersguide.mvp.constantclass.INFORMATION
import com.codemobile.mobilephonebuyersguide.nonmvp.internet.ApiInterface
import com.codemobile.mobilephonebuyersguide.nonmvp.model.ImageResponse
import com.codemobile.mobilephonebuyersguide.nonmvp.model.MobileListResponse
import kotlinx.android.synthetic.main.activity_mobile_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    lateinit var mobileInfo:MobileListResponse
    lateinit var callImageMobile: Call<List<ImageResponse>>
    lateinit var imageAdapter: ImageMobileListAdapter
    private var imageArrayList : ArrayList<ImageResponse> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobile_detail)

        setupData()
        feedImageMobile()
        initDetail()
    }

    private fun setupData() {
        imageAdapter = ImageMobileListAdapter(this)
        detail_rcv.setAdapter(imageAdapter)
        detail_rcv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        mobileInfo = intent.extras?.getSerializable(INFORMATION) as MobileListResponse
        detail_toolbar.setNavigationOnClickListener {
            finish()
        }
    }


    private fun feedImageMobile() {
        callImageMobile = ApiInterface.getBase().getMobileImage(mobileInfo.id.toString())
        //check request
        callImageMobile.enqueue(object : Callback<List<ImageResponse>> {
            override fun onFailure(call: Call<List<ImageResponse>>, t: Throwable) {
            }
            override fun onResponse(call: Call<List<ImageResponse>>, response: Response<List<ImageResponse>>) {
                if (response.isSuccessful){
                    imageArrayList.addAll(response.body()!!)
                    imageAdapter.sublitList(imageArrayList)
                }
            }
        })
    }

    private fun initDetail() {
        if (::mobileInfo.isLateinit){
            txt_detailBrand.text        = mobileInfo.brand
            txt_detailName.text         = mobileInfo.name
            txt_detailPrice.text        = mobileInfo.price.toString()
            txt_detailRating.text       = mobileInfo.rating.toString()
            txt_detailDescription.text  = mobileInfo.description
        }
    }
}
