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

class AlphStatusBarActivity : MyAbsActivity() {
    override fun setContentLayout(): Int = R.layout.activity_no_status_bar

    override fun initView() {
//        immersiveStyle()
//        immersiveStatus()


        alphStatus()
        showToolbar()
        abstract_tool_bar.setBackgroundColor(Color.YELLOW)
        abstract_tool_bar.setTitle("透明状态栏")
    }

    private fun alphStatus() {

        //Android 6.0+ （亮色状态栏）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            window.statusBarColor = Color.BLUE

            window.statusBarColor = Color.TRANSPARENT
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            //            val statusBarView = View(this).apply {
            //                setBackgroundColor(Color.TRANSPARENT)
            //                layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight())
            //            }
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        }
    }


}
