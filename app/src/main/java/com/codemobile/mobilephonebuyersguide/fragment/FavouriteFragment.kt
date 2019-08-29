package com.codemobile.mobilephonebuyersguide.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codemobile.mobilephonebuyersguide.adapter.MobileListAdapter
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse
import kotlinx.android.synthetic.main.fragment_recyclerview.view.*

class FavouriteFragment :Fragment(){

    private var favoriteAdapter:MobileListAdapter? = null
    private var favoriteArrayList:ArrayList<MobileListResponse> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val _view = inflater.inflate(com.codemobile.mobilephonebuyersguide.R.layout.fragment_recyclerview, container, false)
        init(_view)
        return _view
    }

    private var simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(
        0,
        ItemTouchHelper.LEFT
    ) {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
            val dragFlags = 0
            val swipeFlags = ItemTouchHelper.START
            return ItemTouchHelper.Callback.makeMovementFlags(dragFlags, swipeFlags)
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            val position = viewHolder.adapterPosition
            favoriteArrayList.removeAt(position)
            favoriteAdapter?.notifyItemRemoved(position)
        }
    }

    private fun init(_view:View){
        favoriteAdapter = MobileListAdapter(_view.context,1)
        _view.rcv_frgment.let {
            it.adapter = favoriteAdapter
            it.layoutManager = LinearLayoutManager(context)
            val itemTouchHelper:ItemTouchHelper = ItemTouchHelper(simpleItemTouchCallback);
            itemTouchHelper.attachToRecyclerView(it)
        }
    }

    fun sendDataFav(list:ArrayList<MobileListResponse>?){
        if (list !=null){
            favoriteArrayList = list
            favoriteAdapter?.sublitList(favoriteArrayList)
        }
    }

    fun getUnFav():ArrayList<MobileListResponse>{
        return favoriteArrayList
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