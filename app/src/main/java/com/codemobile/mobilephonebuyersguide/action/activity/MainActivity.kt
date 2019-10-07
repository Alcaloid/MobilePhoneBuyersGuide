package com.codemobile.mobilephonebuyersguide.action.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.viewpager.widget.ViewPager
import com.codemobile.mobilephonebuyersguide.R
import com.codemobile.mobilephonebuyersguide.app.constantclass.LIST_SORT
import com.codemobile.mobilephonebuyersguide.app.adapter.ViewPageAdapter
import com.codemobile.mobilephonebuyersguide.dagger.basement.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    lateinit var fragmentAdapter: ViewPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentAdapter =
            ViewPageAdapter(
                supportFragmentManager
            )
        main_viewPager.let {viewPager->
            viewPager.adapter = fragmentAdapter
            main_tab.setupWithViewPager(viewPager)
            viewPager.addOnPageChangeListener(mainPageChangeListener)
        }
        image_filter.setOnClickListener {
            val builder = AlertDialog.Builder(this)
                .setSingleChoiceItems(LIST_SORT, -1) { dialogInterface, i ->
                    Toast.makeText(this, LIST_SORT[i], Toast.LENGTH_SHORT).show()
                    fragmentAdapter.updateSortTye(LIST_SORT[i])
                    dialogInterface.dismiss()
                }
                .create()
            builder.show()
        }
    }

    private val mainPageChangeListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {}
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
        override fun onPageSelected(position: Int) {
            when (position) {
                0 -> {
                    fragmentAdapter.setUnFavoriteMobile()
                }
                1 -> {
                    fragmentAdapter.setFavoriteMobile()
                }
            }
        }
    }
}
