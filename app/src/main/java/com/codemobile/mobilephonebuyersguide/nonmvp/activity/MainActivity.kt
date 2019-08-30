package com.codemobile.mobilephonebuyersguide.nonmvp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.viewpager.widget.ViewPager
import com.codemobile.mobilephonebuyersguide.*
import com.codemobile.mobilephonebuyersguide.mvp.constantclass.LIST_SORT
import com.codemobile.mobilephonebuyersguide.nonmvp.fragment.FavouriteFragment
import com.codemobile.mobilephonebuyersguide.nonmvp.fragment.MobileListFragment
import com.codemobile.mobilephonebuyersguide.nonmvp.ui.ViewPageAdapter
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

        image_filter.setOnClickListener {
            val builder = AlertDialog.Builder(this)
                .setSingleChoiceItems(LIST_SORT, -1) { dialogInterface, i ->
                    frag1.mobileListSortData(LIST_SORT[i])
                    frag2.favoriteListSortData(LIST_SORT[i])
                    dialogInterface.dismiss()
                }
                .create()
            builder.show()
        }


    }
}
