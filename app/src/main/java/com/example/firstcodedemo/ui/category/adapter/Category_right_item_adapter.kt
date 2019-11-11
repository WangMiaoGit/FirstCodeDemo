package com.example.firstcodedemo.ui.category.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.firstcodedemo.R
import com.example.firstcodedemo.ui.category.Category
import com.example.firstcodedemo.ui.category.CategoryBean

class Category_right_item_adapter(data: MutableList<CategoryBean.Category>?) :
    BaseQuickAdapter<CategoryBean.Category, Category_right_item_adapter.ViewHolder>(R.layout.category_gridview_item, data) {

    override fun convert(holder: ViewHolder?, item: CategoryBean.Category?) {

        val title = item?.title
        val imgUrl = item?.img

        if (title==""){
            holder!!.name.text="123"
        }else{

            holder!!.name.text=title
        }


        if (imgUrl==""){
            Glide.with(mContext)
                .load("https://www.baidu.com/img/bd_logo1.png")
                .into(holder.img)
        }else{
            Glide.with(mContext)
                .load(imgUrl)
                .into(holder.img)
        }


    }
    inner class ViewHolder(itemView: View) : BaseViewHolder(itemView) {
        val img = itemView.findViewById<ImageView>(R.id.category_grid_item_img)!!
        val name = itemView.findViewById<TextView>(R.id.category_grid_item_title)!!
    }

}