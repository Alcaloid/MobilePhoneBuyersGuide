package com.codemobile.mobilephonebuyersguide.fragment

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.codemobile.mobilephonebuyersguide.adapter.MobileListAdapter
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse
import kotlinx.android.synthetic.main.fragment_recyclerview.*
import kotlinx.android.synthetic.main.fragment_recyclerview.view.*
import kotlin.collections.ArrayList


class MobileListFragment :Fragment(),MobileListContract.MobileListView{

    private var mobileArrayList: ArrayList<MobileListResponse> = arrayListOf()
    private var mobileListAdapter:MobileListAdapter? =null
    private var mobilePresentor = MobileListPresentation(this)
    lateinit var _view:View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _view = inflater.inflate(com.codemobile.mobilephonebuyersguide.R.layout.fragment_recyclerview, container, false)
        init(_view)
        return _view
    }

    override fun showMobileList(list: ArrayList<MobileListResponse>) {
        mobileArrayList = list
        mobileListAdapter?.sublitList(mobileArrayList)
    }

    override fun showLoading() {
        _view.pgb_fragment.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        _view.pgb_fragment.visibility = View.GONE
    }

    override fun showErrorMessage() {
        val errorDialog = AlertDialog.Builder(context!!)
            .setTitle("Error")
            .setMessage("Can't feed mobile data")
            .setPositiveButton("Feed Agian",DialogInterface.OnClickListener { dialogInterface, i ->
                mobilePresentor.feedMobileList() })
            .setNegativeButton("Ok",DialogInterface.OnClickListener { dialogInterface, i ->
                 })
            .create()
        errorDialog.show()
    }

    fun mobileListSortData(sortForm:String) {
        mobilePresentor.sortMobile(mobileArrayList,sortForm)
    }

    private fun init(_view:View){
        mobileListAdapter = MobileListAdapter(_view.context,0)
        mobileArrayList.clear()
        mobilePresentor.feedMobileList()
        _view.rcv_frgment.let {
            it.adapter = mobileListAdapter
            it.layoutManager = LinearLayoutManager(context)
        }
    }


    fun getFavData(): ArrayList<MobileListResponse>? {
        val favList = mobileListAdapter?.getFavList()
        return favList
    }

    fun sendUnFavToRemoveheart(list: ArrayList<MobileListResponse>){
        //list is item of fav
        mobileArrayList.forEach {item->
            //very slow!
            item.fav = false
            list.forEach {itemFav->
                if (item.id==itemFav.id){
                    item.fav = true
                }
            }
        }
        mobileListAdapter?.sublitList(mobileArrayList)
    }
}