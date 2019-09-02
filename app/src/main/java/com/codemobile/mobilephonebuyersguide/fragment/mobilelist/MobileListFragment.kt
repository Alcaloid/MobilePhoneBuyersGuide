package com.codemobile.mobilephonebuyersguide.fragment.mobilelist

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.codemobile.mobilephonebuyersguide.adapter.MobileListAdapter
import com.codemobile.mobilephonebuyersguide.internet.BaseSortInterface
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse
import kotlinx.android.synthetic.main.fragment_recyclerview.*
import kotlin.collections.ArrayList


class MobileListFragment : Fragment(),
    MobileListContract.MobileListView,
    BaseSortInterface {

    private var mobileArrayList: ArrayList<MobileListResponse> = arrayListOf()
    private var mobileListAdapter: MobileListAdapter? = null
    private var mobilePresentor = MobileListPresentation(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.codemobile.mobilephonebuyersguide.R.layout.fragment_recyclerview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun showMobileList(list: ArrayList<MobileListResponse>) {
        mobileArrayList = list
        mobileListAdapter?.sublitList(mobileArrayList)
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
            .setPositiveButton("Feed Agian", DialogInterface.OnClickListener { dialogInterface, i ->
                mobilePresentor.feedMobileList()
            })
            .setNegativeButton("Ok", DialogInterface.OnClickListener { dialogInterface, i ->
            })
            .create()
        errorDialog.show()
    }

    override fun updateSortType(sortType: String) {
        mobilePresentor.sortMobile(mobileArrayList,sortType)
    }

    private fun init() {
        mobileListAdapter = MobileListAdapter(context!!, 0)
        mobileArrayList.clear()
        mobilePresentor.feedMobileList()
        rcv_frgment.let {
            it.adapter = mobileListAdapter
            it.layoutManager = LinearLayoutManager(context)
        }
    }


    fun getFavData(): ArrayList<MobileListResponse>? {
        val favList = mobileListAdapter?.getFavList()
        return favList
    }

    override fun checkUnFav(list: ArrayList<MobileListResponse>) {
        mobilePresentor.getCurrentFav(mobileArrayList, list)
    }
}