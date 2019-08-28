package com.codemobile.mobilephonebuyersguide.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.codemobile.mobilephonebuyersguide.R
import com.codemobile.mobilephonebuyersguide.adapter.FavoriteAdapter
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse
import kotlinx.android.synthetic.main.fragment_recyclerview.view.*

interface Update{
    fun update()
}

class FavouriteFragment :Fragment(),Update{

    lateinit var favoriteAdapter:FavoriteAdapter
    companion object{
        var favoriteItem:ArrayList<MobileListResponse> = arrayListOf()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val _view = inflater.inflate(R.layout.fragment_recyclerview, container, false)
        init(_view)
        return _view
    }

    override fun onResume() {
        super.onResume()
        favoriteAdapter.notifyDataSetChanged()
    }

    private fun init(_view:View){
        favoriteAdapter = FavoriteAdapter(_view.context, favoriteItem)
        _view.rcv_frgment.let {
            it.adapter = favoriteAdapter
            it.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun update() {
        favoriteAdapter.notifyDataSetChanged()
    }

}