package com.example.firstcodedemo.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.firstcodedemo.R
import kotlinx.android.synthetic.main.activity_collap.*
import kotlinx.android.synthetic.main.activity_md.*

class CollapActivity : MyAbsActivity() {
    override fun setContentLayout()=R.layout.activity_collap

    override fun initView() {
        setSupportActionBar(toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_black)
    }


}
