package com.codemobile.mobilephonebuyersguide.function

import com.codemobile.mobilephonebuyersguide.constantclass.PRICE_HIGHTOLOW
import com.codemobile.mobilephonebuyersguide.constantclass.PRICE_LOWTOHIGH
import com.codemobile.mobilephonebuyersguide.constantclass.RATE_5_1
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse

abstract class MobileFunction {

    var dataSort:String? = null

    fun sortMobile(dataMobile:ArrayList<MobileListResponse>, type:String):ArrayList<MobileListResponse>{
        dataSort = type
        when (type) {
            PRICE_LOWTOHIGH -> {
                dataMobile.sortBy { it.price }
            }
            PRICE_HIGHTOLOW -> {
                dataMobile.sortByDescending { it.price }
            }
            RATE_5_1 -> {
                dataMobile.sortByDescending { it.rating }
            }
            else -> {
                dataMobile.sortBy { it.price }
            }
        }
        return dataMobile
    }

    fun checkSortData(dataMobile:ArrayList<MobileListResponse>):ArrayList<MobileListResponse>{
        if (dataSort!=null){
            return sortMobile(dataMobile, dataSort!!)
        }
        return dataMobile
    }
}