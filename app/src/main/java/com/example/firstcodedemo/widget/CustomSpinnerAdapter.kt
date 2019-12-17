package com.example.firstcodedemo.widget

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.firstcodedemo.R
import com.example.firstcodedemo.bean.SipnnerData
import kotlinx.android.synthetic.main.custom_part_shadow_popup.view.*

class CustomSpinnerAdapter(data: List<String>) :
    BaseQuickAdapter<String, CustomSpinnerAdapter.ViewHolder>(
        R.layout.item_pop_spinner,data
    ) {

    override fun convert(holder: ViewHolder?, item: String?) {

        holder?.tvSpinner?.text = item!!
    }


    inner class ViewHolder(itemView: View) : BaseViewHolder(itemView) {
        val tvSpinner = itemView.findViewById<TextView>(R.id.spinner_tv)!!
    }
}