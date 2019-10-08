package com.codemobile.mobilephonebuyersguide.action.activity.detail

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.codemobile.mobilephonebuyersguide.action.center.MessageFunction
import com.codemobile.mobilephonebuyersguide.action.model.ImageResponse
import com.codemobile.mobilephonebuyersguide.action.model.MobileListResponse
import com.codemobile.mobilephonebuyersguide.action.ui.CirclePagerIndicatorDecoration
import com.codemobile.mobilephonebuyersguide.app.activity.detail.DetailContract
import com.codemobile.mobilephonebuyersguide.app.adapter.ImageMobileListAdapter
import com.codemobile.mobilephonebuyersguide.app.constantclass.INFORMATION
import com.codemobile.mobilephonebuyersguide.dagger.basement.BaseActivity
import kotlinx.android.synthetic.main.activity_mobile_detail.*
import javax.inject.Inject


class DetailActivity : BaseActivity(),
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
        imageList.forEach {
            if (!it.url.contains("http")) {
                it.url = "https://${it.url}"
            }
        }
        imageAdapter.submitList(imageList)
    }

    override fun showErrorMessage() {
        val errorDialog = AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage("Can't Feed data")
            .setPositiveButton("") { _, _ -> }
            .create()
        errorDialog.show()
    }

    private fun setupData() {
        val helper = LinearSnapHelper()
        imageAdapter =
            ImageMobileListAdapter(this)
        detail_rcv.let {
            it.adapter = imageAdapter
            it.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            helper.attachToRecyclerView(it)
            it.addItemDecoration(CirclePagerIndicatorDecoration())
        }
        presenter =
            DetailPresentation(this)
        mobileInfo = intent.extras?.getSerializable(INFORMATION) as MobileListResponse
        presenter.getPassData(mobileInfo)
        presenter.feedImageDetail(mobileInfo.id)
        detail_toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}
