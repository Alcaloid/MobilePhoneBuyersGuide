package com.codemobile.mobilephonebuyersguide.activity

class MainPresentantion :MainContact.Presentation{
    private lateinit var mainContact:MainContact.View

    fun MainPresentantion(view: MainContact.View){
        this.mainContact = view
    }
}