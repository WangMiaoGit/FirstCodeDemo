package com.example.firstcodedemo.activity

import android.graphics.Color
import android.view.Menu
import android.view.View
import com.example.firstcodedemo.R
import com.example.firstcodedemo.fragment.MDFragment
import kotlinx.android.synthetic.main.activity_fragment.*


class FragmentActivity : MyAbsActivity() {



    private var detailFragment: MDFragment? = null
    override fun setContentLayout() = R.layout.activity_fragment

    override fun initView() {

        detailFragment= MDFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.detail_content, detailFragment!!).commit()

        immersiveStatus()

        //状态栏和Toolbar重合  相当于是Activity的颜色
//        window.decorView.systemUiVisibility =
//            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//        window.statusBarColor = Color.TRANSPARENT
        product_detail_tv_send_port.isSelected=true
//        product_detail_tv_send_port.setTextColor(resources.getColor(R.color.colorWhit))
        initText()
    }

    private fun initText() {

        product_detail_tv_send_port.setOnClickListener {
            product_detail_tv_send_port.isSelected=true
            product_detail_tv_send_door.isSelected=false
//            product_detail_tv_send_port.setTextColor(resources.getColor(R.color.colorWhit))
//            product_detail_tv_send_door.setTextColor(resources.getColor(R.color.main_text))

        }
        product_detail_tv_send_door.setOnClickListener {
            product_detail_tv_send_port.isSelected=false
//            product_detail_tv_send_door.setTextColor(resources.getColor(R.color.colorWhit))
//            product_detail_tv_send_port.setTextColor(resources.getColor(R.color.main_text))
            product_detail_tv_send_door.isSelected=true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }


}
