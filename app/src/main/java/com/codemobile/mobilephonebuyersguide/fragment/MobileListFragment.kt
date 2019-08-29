package com.codemobile.mobilephonebuyersguide.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.codemobile.mobilephonebuyersguide.adapter.MobileListAdapter
import com.codemobile.mobilephonebuyersguide.internet.ApiInterface
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse
import kotlinx.android.synthetic.main.fragment_recyclerview.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
        //check request
        Log.d("SCB_Network", callMobileList.request().url().toString())
        callMobileList.enqueue(object : Callback<List<MobileListResponse>> {
            override fun onFailure(call: Call<List<MobileListResponse>>, t: Throwable) {
                Log.d("SCB_Network_Error",t.message.toString())
            }
            override fun onResponse(call: Call<List<MobileListResponse>>, response: Response<List<MobileListResponse>>) {
                Log.d("SCB_Network",response.body().toString())
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

    fun mobileListSortData(sortForm:String){
        when(sortForm){
            "PriceLow"->{
                mobileArrayList.sortBy { it.price }
            }
            "PriceHigh"->{
                mobileArrayList.sortByDescending {
                    it.price
                }
            }
            "Rate"->{
                mobileArrayList.sortByDescending { it.rating }
            }
            else->{
                mobileArrayList.sortBy { it.price }
            }
        }
        mobileListAdapter?.sublitList(mobileArrayList)
    }

}