package com.codemobile.mobilephonebuyersguide.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.viewpager.widget.ViewPager
import com.codemobile.mobilephonebuyersguide.R
import com.codemobile.mobilephonebuyersguide.constantclass.LIST_SORT
import com.codemobile.mobilephonebuyersguide.database.AppDatabase
import com.codemobile.mobilephonebuyersguide.database.CMWorkerThread
import com.codemobile.mobilephonebuyersguide.database.DatabaseEntity
import com.codemobile.mobilephonebuyersguide.fragment.mobilelist.MobileListFragment
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse
import com.codemobile.mobilephonebuyersguide.ui.ViewPageAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var fragmentAdapter: ViewPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentAdapter = ViewPageAdapter(supportFragmentManager)
        main_viewPager.adapter = fragmentAdapter
        main_tab.setupWithViewPager(main_viewPager)
        main_viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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
        })
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

//    fun saveFavorite(targetList: ArrayList<MobileListResponse>) {
//        var mCMWorkerThread: CMWorkerThread = CMWorkerThread("favoritedatabase").also {
//            it.start()
//        }
//        val appDatabase = AppDatabase.getInstance(this).also {
//            it.openHelper.readableDatabase
//        }
//        targetList.forEach { target ->
//            val task = Runnable {
//                appDatabase.favoriteDao().addFavorite(
//                    DatabaseEntity(
//                        target.id, target.name
//                        , target.description, target.brand, target.price, target.rating, target.thumbImageURL, target.fav
//                    )
//                )
//            }
//            mCMWorkerThread.postTask(task)
//        }
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        if (::fragmentAdapter.isLateinit) {
//            //save favorite
//            val favOnMobileList: ArrayList<MobileListResponse>? = fragmentAdapter.getFavoriteMobile()
//            val favOnFavList: ArrayList<MobileListResponse>? = fragmentAdapter.getUnFavoriteMobile()
//
//            if (fragmentAdapter.fragmentManager.fragments.get(currentPage) is MobileListFragment) {
//
//            }
//        }
//    }
}
