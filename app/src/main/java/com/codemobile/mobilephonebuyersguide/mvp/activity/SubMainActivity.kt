package com.codemobile.mobilephonebuyersguide.mvp.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.codemobile.mobilephonebuyersguide.R

class SubMainActivity : AppCompatActivity(), SubMainContract.SubMainView{

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)

    }
    override fun showPage() {

    }

}