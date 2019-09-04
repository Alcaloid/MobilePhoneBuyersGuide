package com.codemobile.mobilephonebuyersguide.fragment.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codemobile.mobilephonebuyersguide.adapter.MobileListAdapter
import com.codemobile.mobilephonebuyersguide.internet.BaseSortInterface
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_recyclerview.view.*

class FavouriteFragment : Fragment(), FavoriteContract.favView,
    BaseSortInterface {

    private var favoriteAdapter: MobileListAdapter? = null
    private var favoriteArrayList: ArrayList<MobileListResponse> = arrayListOf()
    private val favPresentation: FavoritePresentation =
        FavoritePresentation(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.codemobile.mobilephonebuyersguide.R.layout.fragment_recyclerview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        favPresentation.initDatabase(view.context)
    }

    override fun showMobileFav(mobileFav: ArrayList<MobileListResponse>) {
        favoriteArrayList = mobileFav
        favoriteAdapter?.sublitList(favoriteArrayList)
    }

    override fun updateSortType(sortType: String) {
        favPresentation.sortMobile(favoriteArrayList, sortType)
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
            favPresentation.removeMobileFav(favoriteArrayList, position)
        }
    }

    private fun init(_view: View) {
        setFavAdapter(_view)
        _view.rcv_frgment.let {
            it.adapter = favoriteAdapter
            it.layoutManager = LinearLayoutManager(context)
            val itemTouchHelper: ItemTouchHelper = ItemTouchHelper(simpleItemTouchCallback);
            itemTouchHelper.attachToRecyclerView(it)
        }
    }

    fun setFavAdapter(_view: View) {
        favoriteAdapter = MobileListAdapter(1, object : MobileListAdapter.MobileAdapterInterface {
            override fun addFavMobile(target: MobileListResponse) {}

            override fun removeFavMobile(target: MobileListResponse) {}

            override fun setImage(imageTarget: ImageView, imageURL: String) {
                favPresentation.setImageTarget(_view.context, imageTarget, imageURL)
            }

            override fun gotoDetailPage(infomation: MobileListResponse) {
                favPresentation.gotoDetailPage(_view.context, infomation)
            }
        })
    }

    fun sendDataFav(list: ArrayList<MobileListResponse>?) {
        favPresentation.setMobileFav(list)
    }

    fun getUnFav(): ArrayList<MobileListResponse> {
        return favoriteArrayList
    }

}