package com.codemobile.mobilephonebuyersguide.activity.detail

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.codemobile.mobilephonebuyersguide.adapter.ImageMobileListAdapter
import com.codemobile.mobilephonebuyersguide.constantclass.INFORMATION
import com.codemobile.mobilephonebuyersguide.model.ImageResponse
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse
import kotlinx.android.synthetic.main.activity_mobile_detail.*
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader


class DetailActivity : AppCompatActivity(),
    DetailContract.DetailView {

    lateinit var mobileInfo: MobileListResponse
    lateinit var imageAdapter: ImageMobileListAdapter
    lateinit var presentor: DetailContract.DetailPresenttaion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.codemobile.mobilephonebuyersguide.R.layout.activity_mobile_detail)

        setupData()
        /*val testArray:ArrayList<String> = arrayListOf("10","20","30")
        var count = 0
        btn_test_write.setOnClickListener {
            val file:String = "testingText"
            val data:String = testArray[count]
            count += 1
            val fileOutputStream: FileOutputStream
            try {
                fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
                fileOutputStream.write(data.toByteArray())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
        btn_test_load.setOnClickListener {
            var fileInputStream: FileInputStream? = null
            fileInputStream = openFileInput("testingText")
            var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder: StringBuilder = StringBuilder()
            var text: String? = null
            while ({ text = bufferedReader.readLine();
                    text }() != null) {
                stringBuilder.append(text)
            }
            stringBuilder.forEach {
                println(it)
            }
            bufferedReader.close()
        }
        btn_test_delete.setOnClickListener {
            this.deleteFile("testingText")
        }*/
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
