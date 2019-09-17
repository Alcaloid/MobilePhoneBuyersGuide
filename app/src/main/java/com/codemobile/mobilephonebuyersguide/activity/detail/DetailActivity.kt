package com.codemobile.mobilephonebuyersguide.activity.detail

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.codemobile.mobilephonebuyersguide.adapter.ImageMobileListAdapter
import com.codemobile.mobilephonebuyersguide.constantclass.INFORMATION
import com.codemobile.mobilephonebuyersguide.model.ImageResponse
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse
import kotlinx.android.synthetic.main.activity_mobile_detail.*


class DetailActivity : AppCompatActivity(),
    DetailContract.DetailView {

    private lateinit var mobileInfo: MobileListResponse
    private lateinit var imageAdapter: ImageMobileListAdapter
    private lateinit var presenter: DetailContract.DetailPresentation

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

    override fun showImageMobileList(imageList: List<ImageResponse>) {
        imageAdapter.submitList(imageList)
    }

    override fun showErrorMessage() {
        val errorDialog = AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage("Can't feed mobile image")
            .setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->
            })
            .create()
        errorDialog.show()
    }

    private fun setupData() {
        imageAdapter = ImageMobileListAdapter(this)
        detail_rcv.let {
            it.adapter = imageAdapter
            it.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        }
        presenter = DetailPresentation(this)
        mobileInfo = intent.extras?.getSerializable(INFORMATION) as MobileListResponse
        presenter.getPassData(mobileInfo)
        presenter.feedImageDetail(mobileInfo.id)
        detail_toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}
