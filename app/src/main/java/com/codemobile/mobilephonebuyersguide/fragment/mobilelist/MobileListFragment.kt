package com.codemobile.mobilephonebuyersguide.fragment.mobilelist

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.codemobile.mobilephonebuyersguide.adapter.MobileListAdapter
import com.codemobile.mobilephonebuyersguide.internet.BaseSortInterface
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_recyclerview.*
import kotlin.collections.ArrayList


class MobileListFragment : Fragment(),
    MobileListContract.MobileListView,
    BaseSortInterface {

    private var mobileListAdapter: MobileListAdapter? = null
    private var mobilePresentor = MobileListPresentation(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.codemobile.mobilephonebuyersguide.R.layout.fragment_recyclerview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    override fun showMobileList(list: ArrayList<MobileListResponse>) {
//        list.forEach {
//            println(it)
//        }
        mobileListAdapter?.sublitList(list)
    }

    override fun showLoading() {
        pgb_fragment.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pgb_fragment.visibility = View.GONE
    }

    override fun showErrorMessage() {
        val errorDialog = AlertDialog.Builder(context!!)
            .setTitle("Error")
            .setMessage("Can't feed mobile data")
            .setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->
            })
            .create()
        errorDialog.show()
    }

    override fun updateSortType(sortType: String) {
        mobilePresentor.sortMobile(sortType)
    }

    private fun init(view: View) {
        setMobileAdapter(view)
        mobilePresentor.setupDatabase(view.context)
        mobilePresentor.checkPreviousFavorite()
        mobilePresentor.feedMobileList()
        rcv_frgment.let {
            it.adapter = mobileListAdapter
            it.layoutManager = LinearLayoutManager(view.context)
        }
        srl_refreshData.setOnRefreshListener {
            mobilePresentor.feedMobileList()
        }
    }

    fun setMobileAdapter(view: View) {
        mobileListAdapter = MobileListAdapter(0, object : MobileListAdapter.MobileAdapterInterface {
            override fun addFavMobile(target: MobileListResponse) {
                mobilePresentor.addFavoriteMobile(target)
            }

            override fun removeFavMobile(target: MobileListResponse) {
                mobilePresentor.removeFavoriteMobile(target)
            }

            override fun setImage(imageTarget: ImageView, imageURL: String) {
                Picasso.with(view.context).load(imageURL).into(imageTarget)
            }

            override fun gotoDetailPage(infomation: MobileListResponse) {
                mobilePresentor.gotoDetailPage(view.context, infomation)
            }
        })
    }

    override fun closeRefresh() {
        srl_refreshData.isRefreshing = false
    }

    override fun setPreFavorite() {
        mobilePresentor.getCurrentFav(mobilePresentor.getFavoriteMobile())
    }

    fun getFavData(): ArrayList<MobileListResponse>? {
        return mobilePresentor.getFavoriteMobile()
    }

    fun checkUnFav(list: ArrayList<MobileListResponse>?) {
        mobilePresentor.getCurrentFav(list)
    }
}