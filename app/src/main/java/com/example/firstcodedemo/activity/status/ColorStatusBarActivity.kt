package com.example.firstcodedemo.activity.status

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.example.firstcodedemo.R
import com.example.firstcodedemo.activity.MyAbsActivity
import kotlinx.android.synthetic.main.activity_abstract.*

class ColorStatusBarActivity : MyAbsActivity() {
    override fun setContentLayout(): Int = R.layout.activity_no_status_bar

    override fun initView() {

//        immersiveStyle()
        colorStatus()
        showToolbar()
        abstract_tool_bar.setTitle("带颜色状态栏")
        abstract_tool_bar.setBackgroundColor(Color.YELLOW)
    }


    private fun colorStatus() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = Color.GREEN//设置状态栏颜色
//            window.statusBarColor = Color.TRANSPARENT//设置状态栏颜色 和背景色  相关
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR//实现状态栏图标和文字颜色为暗色
        }else {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        }

    }


}
