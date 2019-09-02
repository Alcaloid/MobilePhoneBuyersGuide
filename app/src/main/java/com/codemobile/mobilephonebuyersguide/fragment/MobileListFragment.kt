package com.codemobile.mobilephonebuyersguide.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.codemobile.mobilephonebuyersguide.constantclass.PRICE_HIGHTOLOW
import com.codemobile.mobilephonebuyersguide.constantclass.PRICE_LOWTOHIGH
import com.codemobile.mobilephonebuyersguide.constantclass.RATE_5_1
import com.codemobile.mobilephonebuyersguide.adapter.MobileListAdapter
import com.codemobile.mobilephonebuyersguide.internet.ApiInterface
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse
import kotlinx.android.synthetic.main.fragment_recyclerview.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList


class MobileListFragment :Fragment(),MobileListContract.View{

    private var mobileArrayList: ArrayList<MobileListResponse> = arrayListOf()
    private var mobileListAdapter:MobileListAdapter? =null
    lateinit var mobilePresentor: MobileListContract.Presentor

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val _view = inflater.inflate(com.codemobile.mobilephonebuyersguide.R.layout.fragment_recyclerview, container, false)
        init(_view)
        return _view
    }

    override fun showMobileList(mobileList: List<MobileListResponse>) {
        mobileListAdapter?.sublitList(mobileList)
    }

    private fun init(_view:View){
        mobileListAdapter = MobileListAdapter(_view.context,0)
        mobilePresentor = MobileListPresentor(this)
        mobilePresentor.feedMobile()
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

    fun mobileListSortData(sortForm:String){
        when(sortForm){
            PRICE_LOWTOHIGH ->{
                mobileArrayList.sortBy { it.price }
            }
            PRICE_HIGHTOLOW ->{
                mobileArrayList.sortByDescending { it.price }
            }
            RATE_5_1 ->{
                mobileArrayList.sortByDescending { it.rating }
            }
            else->{
                mobileArrayList.sortBy { it.price }
            }
        }
        mobileListAdapter?.sublitList(mobileArrayList)
    }

}