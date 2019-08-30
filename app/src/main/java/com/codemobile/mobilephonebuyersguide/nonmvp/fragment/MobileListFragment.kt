package com.codemobile.mobilephonebuyersguide.nonmvp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.codemobile.mobilephonebuyersguide.mvp.constantclass.PRICE_HIGHTOLOW
import com.codemobile.mobilephonebuyersguide.mvp.constantclass.PRICE_LOWTOHIGH
import com.codemobile.mobilephonebuyersguide.mvp.constantclass.RATE_5_1
import com.codemobile.mobilephonebuyersguide.nonmvp.adapter.MobileListAdapter
import com.codemobile.mobilephonebuyersguide.nonmvp.internet.ApiInterface
import com.codemobile.mobilephonebuyersguide.nonmvp.model.MobileListResponse
import kotlinx.android.synthetic.main.fragment_recyclerview.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList


class MobileListFragment :Fragment(){

    private var mobileArrayList: ArrayList<MobileListResponse> = arrayListOf()
    private var mobileListAdapter:MobileListAdapter? =null
    lateinit var callMobileList: Call<List<MobileListResponse>>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val _view = inflater.inflate(com.codemobile.mobilephonebuyersguide.R.layout.fragment_recyclerview, container, false)
        init(_view)
        feedMobile()
        return _view
    }

    private fun init(_view:View){
        mobileListAdapter = MobileListAdapter(_view.context,0)
        _view.rcv_frgment.let {
            it.adapter = mobileListAdapter
            it.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun feedMobile() {
        mobileArrayList.clear()
        callMobileList = ApiInterface.getBase().getMobileList()
        callMobileList.enqueue(object : Callback<List<MobileListResponse>> {
            override fun onFailure(call: Call<List<MobileListResponse>>, t: Throwable) {
            }
            override fun onResponse(call: Call<List<MobileListResponse>>, response: Response<List<MobileListResponse>>) {
                if (response.isSuccessful){
                    mobileArrayList.addAll(response.body()!!)
                    mobileListAdapter?.sublitList(mobileArrayList)
                }
            }
        })
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