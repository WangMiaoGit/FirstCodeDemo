package com.example.firstcodedemo.ui.captcha

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.firstcodedemo.R
import kotlinx.android.synthetic.main.activity_img_capthca.*
import android.widget.Toast
import android.text.TextUtils
import android.util.Log
import com.example.firstcodedemo.activity.MyAbsActivity
import com.example.firstcodedemo.utils.toast
import com.example.firstcodedemo.widget.ChooseSignUpDialog
import java.security.AccessController.getContext


//图形验证码
class ImgCapthcaActivity : MyAbsActivity(), View.OnClickListener {

    private lateinit var chooseDialog: ChooseSignUpDialog

    override fun setContentLayout(): Int = R.layout.activity_img_capthca

    override fun initView() {
        chooseDialog = ChooseSignUpDialog(this)


        //设置接口实现
        chooseDialog.setOptionListener(object : ChooseSignUpDialog.OptionListener {
            override fun cancel() {

                closeOptionDialog()
            }

            override fun goToPersonal(context: Context) {

                "Open Personal".toast(this@ImgCapthcaActivity)
            }

            override fun goToCompany(context: Context) {

                "Open Company".toast(this@ImgCapthcaActivity)
            }

        })

        chooseDialog.initView(this)

        getCode()
        btn.setOnClickListener(this)
        btn_submit.setOnClickListener(this)
        btn_choose.setOnClickListener(this)


    }

    private var codeStr: String? = null
    private var codeUtils: CodeUtils? = null


    //获取验证码
    private fun getCode() {
        codeUtils = CodeUtils.instance
        val bitmap = codeUtils!!.createBitmap()
        image.setImageBitmap(bitmap)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn -> getCode()
            R.id.btn_submit -> {
                codeStr = et.text.toString().trim()
                Log.e("codeStr", codeStr)
                if (null == codeStr || TextUtils.isEmpty(codeStr)) {
                    "请输入验证码".toast(this@ImgCapthcaActivity)
                    return
                }
                val code = codeUtils?.getCode()
                Log.e("code", code)
                if (code!!.equals(codeStr, ignoreCase = true)) {//判断验证码不检验大小写
                    "验证码正确".toast(this@ImgCapthcaActivity)
                    et.setText("")
                } else {

                    "验证码错误".toast(this@ImgCapthcaActivity)
                    et.setText("")
                    getCode()
                }
            }
            R.id.btn_choose -> {
                showOptionDialog()
            }
        }
    }


    /**
     * 弹出Option弹窗（与[ProductDetailFragment]共用）
     */
    fun showOptionDialog() {
        chooseDialog.showDialog()
    }

    fun closeOptionDialog() {
        chooseDialog.closeDialog()
    }
}
