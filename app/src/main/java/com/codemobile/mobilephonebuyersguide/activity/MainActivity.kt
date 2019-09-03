package com.codemobile.mobilephonebuyersguide.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.viewpager.widget.ViewPager
import com.codemobile.mobilephonebuyersguide.*
import com.codemobile.mobilephonebuyersguide.constantclass.LIST_SORT
import com.codemobile.mobilephonebuyersguide.ui.ViewPageAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentAdapter = ViewPageAdapter(supportFragmentManager)
        main_viewPager.adapter = fragmentAdapter
        main_tab.setupWithViewPager(main_viewPager)
        main_viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                when (position) {
                    0->{
                        fragmentAdapter.setUnFavoriteMobile()
                    }
                    1->{
                        fragmentAdapter.setFavoriteMobile()
                    }
                }
            }
        })
        image_filter.setOnClickListener {
            val builder = AlertDialog.Builder(this)
                .setSingleChoiceItems(LIST_SORT, -1) { dialogInterface, i ->
                    fragmentAdapter.updateSortTye(LIST_SORT[i])
                    dialogInterface.dismiss()
                }
                .create()
            builder.show()
        }
    }
}
