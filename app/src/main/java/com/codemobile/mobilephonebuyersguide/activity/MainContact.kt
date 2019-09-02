package com.codemobile.mobilephonebuyersguide.activity

import android.view.View
import androidx.viewpager.widget.ViewPager

interface MainContact {
    interface View{
        fun setSortOnClick(itemClick:android.view.View.OnClickListener)
        fun setOnPageClick(pageClick:ViewPager.OnPageChangeListener)
    }

    interface Presentation{

    }
}