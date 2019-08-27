package com.codemobile.mobilephonebuyersguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codemobile.mobilephonebuyersguide.ui.ViewPageAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    private val onPageChangeListener = TabLayout.TabLayoutOnPageChangeListener(main_tab)
//    private val onTabSelectListener = object :TabLayout.OnTabSelectedListener{
//        override fun onTabReselected(p0: TabLayout.Tab) {
//        }
//
//        override fun onTabUnselected(p0: TabLayout.Tab) {
//        }
//
//        override fun onTabSelected(page: TabLayout.Tab) {
//            main_container.currentItem = page.position
//        }
//
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val fragmentAdapter = ViewPageAdapter(supportFragmentManager)
//        main_container.adapter = fragmentAdapter
//        main_container.addOnPageChangeListener(onPageChangeListener)
//
//        main_tab.addOnTabSelectedListener(onTabSelectListener)
    }
}
