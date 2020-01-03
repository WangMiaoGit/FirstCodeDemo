package com.example.firstcodedemo.activity.status

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import com.example.firstcodedemo.R
import com.example.firstcodedemo.activity.MyAbsActivity
import kotlinx.android.synthetic.main.activity_abstract.*
import kotlinx.android.synthetic.main.activity_no_status_bar.*

class NoStatusBarActivity : MyAbsActivity() {
    override fun setContentLayout(): Int = R.layout.activity_no_status_bar
    private val countryData = ArrayList<String>()
    override fun initView() {

        noStatus()
        showToolbar()
        abstract_tool_bar.setTitle("没有状态栏")

        //spinner 的  排序搜索
        countryData.addAll(listOf("1", "2", "3", "4", "5"))
        list_spinner.attachDataSource(countryData)


        btn_change_data.setOnClickListener {

            //spinner  修改了数据源  就能改变其中显示的  数据
            val chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
            //从chars中随机取出一个字符
            val random = chars.random()
            countryData[4]=random.toString()
        }




        initAnimation()
        btn_change_animation.setOnClickListener {

            if(load_img_animation.animation!=null){
                load_img_animation.clearAnimation()
            }else{
                load_img_animation.startAnimation(translateAnimation)
            }
        }
    }


    private fun noStatus() {

        //取消状态栏  看不到
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

    }

    private lateinit var translateAnimation:TranslateAnimation
    private fun initAnimation(){
         translateAnimation =  TranslateAnimation(0f,0f,0f,100f)
        // 创建平移动画的对象：平移动画对应的Animation子类为TranslateAnimation
        // 参数分别是：
        // 1. fromXDelta ：视图在水平方向x 移动的起始值
        // 2. toXDelta ：视图在水平方向x 移动的结束值
        // 3. fromYDelta ：视图在竖直方向y 移动的起始值
        // 4. toYDelta：视图在竖直方向y 移动的结束值
        translateAnimation.duration = 1000

        //来回重复效果
        translateAnimation.repeatCount = Animation.INFINITE
        translateAnimation.repeatMode = Animation.REVERSE

        // 播放动画直接 startAnimation(translateAnimation)
        //如：
//        load_img_animation.startAnimation(translateAnimation)
    }


}
