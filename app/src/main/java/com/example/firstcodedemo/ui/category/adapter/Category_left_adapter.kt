package com.example.firstcodedemo.ui.category.adapter

import android.util.SparseBooleanArray
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.firstcodedemo.R
import com.example.firstcodedemo.ui.category.CategoryBean
import android.graphics.Color.parseColor
import android.graphics.Color



class Category_left_adapter(data: MutableList<CategoryBean>?) :
    BaseQuickAdapter<CategoryBean, Category_left_adapter.ViewHolder>(R.layout.category_item_left, data) {

    private var currentSelected: Int = 0

    override fun convert(helper: ViewHolder?, item: CategoryBean?) {

        val type = item!!.type
//        helper!!.setTextColor(R.id.item_category_parent_tv_name,
//            if (helper.layoutPosition == currentSelected) R.drawable.selectde_text_bg else R.drawable.unselectde_text_bg)
//        helper!!.setText(R.id.item_category_parent_tv_name, type)
//        helper!!.tvName.setTextColor(
//            if (helper.getLayoutPosition() === currentSelected) R.drawable.selectde_text_bg else R.drawable.unselectde_text_bg
//        )

//        helper!!.tvName.setTextColor(
//            if (helper.getLayoutPosition() === currentSelected) parseColor("#8470FF") else parseColor(
//                "#363636"
//            )
//        )
//        helper!!.tvName.setTextColor(if (helper.layoutPosition == currentSelected) R.drawable.selectde_text_bg else R.drawable.unselectde_text_bg)
//        helper!!.tvName.setTextColor(if (helper.layoutPosition == currentSelected) parseColor("#FFFFFF") else parseColor("#000000"))
//        helper!!.tvName.setBackgroundResource(R.drawable.selector_category_parent)
        helper!!.tvName.text=type

//        println("选中了+$currentSelected")

        helper.tvName.isSelected = helper.layoutPosition == currentSelected
//
//        helper.tvName.setOnClickListener {
//            if (helper.adapterPosition == currentSelected) { //防止父级分类列表显示错误
//                return@setOnClickListener
//            }
//
//            it.isSelected = true
//            selectedArray.put(helper.adapterPosition, true)
//            selectedArray.put(currentSelected, false)
//            notifyItemChanged(currentSelected)
//            currentSelected = helper.adapterPosition
//            onItemClickListener.onItemClick(this, it, helper.adapterPosition) //将点击事件穿透
//        }

    }

    inner class ViewHolder(itemView: View) : BaseViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.item_category_parent_tv_name)!!
    }

    fun changeSelection(position:Int){
        currentSelected=position
        notifyDataSetChanged()
    }

}