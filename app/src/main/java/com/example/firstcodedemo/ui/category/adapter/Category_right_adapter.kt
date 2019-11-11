package com.example.firstcodedemo.ui.category.adapter

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.GridLayout
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.firstcodedemo.R
import com.example.firstcodedemo.bean.Fruit
import com.example.firstcodedemo.ui.category.Category
import com.example.firstcodedemo.ui.category.CategoryBean

import kotlinx.android.synthetic.main.activity_category_activity.*

class Category_right_adapter(data: MutableList<CategoryBean>?) :
    BaseQuickAdapter<CategoryBean, Category_right_adapter.ViewHolder>(R.layout.category_item_right, data) {

    private lateinit var childItemAdapter:Category_right_item_adapter


    override fun convert(holder: ViewHolder?, item: CategoryBean?) {
        val title = item!!.type

        holder!!.tvName.text=title
        val gridLayoutManager =  GridLayoutManager(mContext, 3)
        //设置RecycleView显示的方向是水平还是垂直 GridLayout.HORIZONTAL水平  GridLayout.VERTICAL默认垂直
        gridLayoutManager.orientation = GridLayout.VERTICAL

        holder.recyclerView.layoutManager = gridLayoutManager


        childItemAdapter = Category_right_item_adapter(item.categoryList)

        childItemAdapter.setOnItemClickListener { adapter, view, position ->
            val item = adapter.data[position]

//            println("点击到了=====================")
            if (item is CategoryBean.Category){
                println("点击到了${item.title}")
            }
        }
//
        holder.recyclerView.adapter=childItemAdapter

    }

    inner class ViewHolder(itemView: View) : BaseViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.category_list_title)!!
        val recyclerView = itemView.findViewById<RecyclerView>(R.id.category_child_item_rv)!!
    }

}