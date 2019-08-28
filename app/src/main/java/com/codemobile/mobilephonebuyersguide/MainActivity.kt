package com.codemobile.mobilephonebuyersguide

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.codemobile.mobilephonebuyersguide.fragment.FavouriteFragment
import com.codemobile.mobilephonebuyersguide.fragment.MobileListFragment
import com.codemobile.mobilephonebuyersguide.ui.ViewPageAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        var testArray:ArrayList<String> = arrayListOf()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentAdapter = ViewPageAdapter(supportFragmentManager)
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

                    }
                    1->{
//                        AsyncTask<>
                        println(FavouriteFragment().favoriteAdapter)
                    }
                    else->{
                       println("FragmentNotResponse")
                    }
                }
            }

        })

        image_filter.setOnClickListener {
            val listItems = arrayOf("Price low to high", "Price high to low", "Rating 5-1")
            var builder = AlertDialog.Builder(this)
                .setSingleChoiceItems(listItems, -1) { dialogInterface, i ->
                    val selectedItem = listItems[i]

                    dialogInterface.dismiss()
                }
                .create()
            builder.show()
        }


    }
}
