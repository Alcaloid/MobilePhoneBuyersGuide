package com.codemobile.mobilephonebuyersguide.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.codemobile.mobilephonebuyersguide.R
import com.codemobile.mobilephonebuyersguide.adapter.MobileListAdapter
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse
import kotlinx.android.synthetic.main.fragment_recyclerview.view.*

class FavouriteFragment :Fragment(){

    private var favoriteAdapter:MobileListAdapter? = null
    private var favoriteArrayList:ArrayList<MobileListResponse> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val _view = inflater.inflate(R.layout.fragment_recyclerview, container, false)
        init(_view)
        return _view
    }

    private fun init(_view:View){
        favoriteAdapter = MobileListAdapter(_view.context,1)
        _view.rcv_frgment.let {
            it.adapter = favoriteAdapter
            it.layoutManager = LinearLayoutManager(context)
        }
    }

    fun sendDataFav(list:ArrayList<MobileListResponse>?){
        if (list !=null){
            favoriteArrayList = list
            favoriteAdapter?.sublitList(favoriteArrayList)
        }
    }

    fun favoriteListSortData(sortForm:String){
        when(sortForm){
            "PriceLow"->{
                favoriteArrayList.sortBy { it.price }
            }
            "PriceHigh"->{
                favoriteArrayList.sortByDescending { it.price }
            }
            "Rate"->{
                favoriteArrayList.sortByDescending { it.rating }
            }
            else->{
                favoriteArrayList.sortBy { it.price }
            }
        }
        println("ItemSize:"+favoriteArrayList.size)
        favoriteAdapter?.sublitList(favoriteArrayList)
    }
}