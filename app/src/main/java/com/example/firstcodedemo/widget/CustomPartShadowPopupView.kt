package com.example.firstcodedemo.widget

import android.content.Context
import com.example.firstcodedemo.widget.dialog.AbstractSipnnerAttachDialog

import android.support.annotation.NonNull
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.widget.ArrayAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.firstcodedemo.R
import com.example.firstcodedemo.bean.SipnnerData
import com.example.firstcodedemo.ui.MyAdapter
import com.lxj.xpopup.impl.PartShadowPopupView
import kotlinx.android.synthetic.main.custom_part_shadow_popup.view.*


//淘宝的商品列表筛选
class CustomPartShadowPopupView(context: Context, list: List<String>,baseAdapter: BaseQuickAdapter<String, CustomSpinnerAdapter.ViewHolder>) :
    PartShadowPopupView(context) {


    private val listData = list

//    lateinit var adapter: CustomSpinnerAdapter
    val adapter=baseAdapter

    override fun getImplLayoutId(): Int = R.layout.custom_part_shadow_popup // 编写你自己的布局

    override fun onCreate() {

       super.onCreate()
        println(listData)

//        adapter = CustomSpinnerAdapter(listData)
        pop_rv_spinner.layoutManager = LinearLayoutManager(context)
        pop_rv_spinner.adapter = adapter


    }

}