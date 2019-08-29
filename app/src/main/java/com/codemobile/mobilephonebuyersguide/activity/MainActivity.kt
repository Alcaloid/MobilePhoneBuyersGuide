package com.codemobile.mobilephonebuyersguide.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.viewpager.widget.ViewPager
import com.codemobile.mobilephonebuyersguide.R
import com.codemobile.mobilephonebuyersguide.fragment.FavouriteFragment
import com.codemobile.mobilephonebuyersguide.fragment.MobileListFragment
import com.codemobile.mobilephonebuyersguide.ui.ViewPageAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var frag1:MobileListFragment
    lateinit var frag2:FavouriteFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        frag1 = MobileListFragment()
        frag2 = FavouriteFragment()

        val fragmentAdapter = ViewPageAdapter(supportFragmentManager,frag1,frag2)
        main_viewPager.adapter = fragmentAdapter
        main_tab.setupWithViewPager(main_viewPager)

        main_viewPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                when(position){
                    0->{
                        val itemUnFav = frag2.getUnFav()
                        frag1.sendUnFavToRemoveheart(itemUnFav)
                    }
                    1->{
                        val itemFav = frag1.getFavData()
                        frag2.sendDataFav(itemFav)
                    }
                }
            }
        })

        val popUpWillCilkListener = View.OnClickListener {

        }
        image_filter.setOnClickListener(popUpWillCilkListener)
        image_filter.setOnClickListener {
            val listItems = arrayOf("Price low to high", "Price high to low", "Rating 5-1")
            val builder = AlertDialog.Builder(this)
                .setSingleChoiceItems(listItems, -1) { dialogInterface, i ->
                    when(i){
                        0 -> {
                            frag1.mobileListSortData("PriceLow")
                            frag2.favoriteListSortData("PriceLow")
                        }
                        1 -> {
                            frag1.mobileListSortData("PriceHigh")
                            frag2.favoriteListSortData("PriceHigh")
                        }
                        2 ->{
                            frag1.mobileListSortData("Rate")
                            frag2.favoriteListSortData("Rate")
                        }
                    }
                    dialogInterface.dismiss()
                }
                .create()
            builder.show()
        }
    }
}
