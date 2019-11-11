package com.example.firstcodedemo.widget

import android.content.Context
import com.example.firstcodedemo.R
import com.example.firstcodedemo.widget.dialog.AbstractBottomDialog
import kotlinx.android.synthetic.main.dialog_chooseignup.view.*

class ChooseSignUpDialog(context: Context) :AbstractBottomDialog(context){

    override fun setContentLayout(): Int= R.layout.dialog_chooseignup


    //接口抽象方法调用
    fun initView(context: Context){
        btn_cancel.setOnClickListener {
            onOptionListener?.cancel()
        }
        btn_choose_personal.setOnClickListener {
            onOptionListener?.goToPersonal(context)
        }
        btn_choose_company.setOnClickListener {
            onOptionListener?.goToCompany(context)
        }
    }


    /**
     * 监听
     */
    private var onOptionListener: OptionListener? = null

    interface OptionListener{
        fun cancel()
        fun goToPersonal(context: Context)
        fun goToCompany(context: Context)
    }
    fun setOptionListener(listener: OptionListener) {
        this.onOptionListener = listener
    }
}