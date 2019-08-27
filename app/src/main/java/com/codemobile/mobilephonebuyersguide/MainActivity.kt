package com.codemobile.mobilephonebuyersguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codemobile.mobilephonebuyersguide.ui.ViewPageAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentAdapter = ViewPageAdapter(supportFragmentManager)
        main_viewPager.adapter = fragmentAdapter

        main_tab.setupWithViewPager(main_viewPager)
    }
}
