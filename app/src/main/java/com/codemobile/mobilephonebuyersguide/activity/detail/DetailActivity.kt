package com.codemobile.mobilephonebuyersguide.activity.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.codemobile.mobilephonebuyersguide.adapter.ImageMobileListAdapter
import com.codemobile.mobilephonebuyersguide.constantclass.INFORMATION
import com.codemobile.mobilephonebuyersguide.model.ImageResponse
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse
import kotlinx.android.synthetic.main.activity_mobile_detail.*


class DetailActivity : AppCompatActivity(),
    DetailContract.DetailView {

    lateinit var mobileInfo: MobileListResponse
    lateinit var imageAdapter: ImageMobileListAdapter
    lateinit var presentor: DetailContract.DetailPresenttaion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.codemobile.mobilephonebuyersguide.R.layout.activity_mobile_detail)
        setupData()
    }

    override fun setName(name: String) {
        txt_detailName.text = name
    }

    override fun setBrand(brand: String) {
        txt_detailBrand.text = brand
    }

    override fun setPrice(price: String) {
        txt_detailPrice.text = price
    }

    override fun setRate(rate: String) {
        txt_detailRating.text = rate
    }

    override fun setDescription(description: String) {
        txt_detailDescription.text = description
    }

    override fun showImageMobileList(imageList: ArrayList<ImageResponse>) {
        imageAdapter.sublitList(imageList)
    }

    private fun setupData() {
        imageAdapter = ImageMobileListAdapter(this)
        detail_rcv.setAdapter(imageAdapter)
        detail_rcv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        presentor = DetailPresentation(this)
        mobileInfo = intent.extras?.getSerializable(INFORMATION) as MobileListResponse
        presentor.getPassData(mobileInfo)
        presentor.feedImageDetail(mobileInfo.id)
        detail_toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}
