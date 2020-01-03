package com.example.firstcodedemo.activity.status

import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager
import com.example.firstcodedemo.R
import com.example.firstcodedemo.activity.MyAbsActivity
import kotlinx.android.synthetic.main.activity_abstract.*
import kotlinx.android.synthetic.main.activity_hint_status_bar.*
import kotlinx.android.synthetic.main.activity_no_status_bar.*
import kotlinx.android.synthetic.main.activity_status_bar.*

class HintStatusBarActivity : MyAbsActivity() {
    override fun setContentLayout(): Int = R.layout.activity_hint_status_bar

    override fun initView() {

        setStatusBar()
        no_status_bar_ll.setPadding(0, this.getStatusBarHeight(), 0, 0)

    }


    //设置状态栏颜色 ||  设置状态栏和背景色
    private fun setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            window.statusBarColor = resources.getColor(R.color.colorAccent)//设置状态栏颜色
            window.statusBarColor = Color.TRANSPARENT//设置状态栏颜色 和背景色  相关
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR//实现状态栏图标和文字颜色为暗色
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        }
    }


}
