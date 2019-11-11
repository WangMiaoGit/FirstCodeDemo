package com.example.firstcodedemo.ui

import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.firstcodedemo.R
import com.example.firstcodedemo.bean.Fruit

//BRVAH 的基本adapter使用
class BRVAH_FruitAdapter(data: MutableList<Fruit>?) :
    BaseQuickAdapter<Fruit, BaseViewHolder>(R.layout.fruit_item, data) {


    override fun convert(helper: BaseViewHolder?, item: Fruit?) {


        val name = item!!.name
        val imgId = item.imgId
        helper!!.setText(R.id.fruit_name, name)

        Glide.with(mContext)
            .load(imgId)
            .into(helper.getView(R.id.fruit_img))

    }

}