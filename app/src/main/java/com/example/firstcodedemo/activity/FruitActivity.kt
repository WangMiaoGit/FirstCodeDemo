package com.example.firstcodedemo.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.firstcodedemo.R
import kotlinx.android.synthetic.main.activity_fruit.*
import java.lang.StringBuilder

//点击每个条目显示的  fruit
class FruitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit)

        val fruitName = intent.getStringExtra("fruitName")
        val fruitId = intent.getIntExtra("fruitId", 0)

        println("fruitName:$fruitName------fruitId:$fruitId")

        setSupportActionBar(toolbar_fruit)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)//左上添加退出图标
        collapsing_layout.title = fruitName//折叠栏的标题

        val roundedCorners = RoundedCorners(6)
        val override = RequestOptions.bitmapTransform(roundedCorners).override(300, 300)


        Glide.with(this).load(fruitId).apply(override).into(image_collapsing_fruit)
        val generateFruitContent = generateFruitContent(fruitName)

        fruit_content_text.text = generateFruitContent
    }

    private fun generateFruitContent(fruitName: String): String {
        val stringBuilder = StringBuilder()
        for (i in 0..500)
            stringBuilder.append(fruitName)

        return stringBuilder.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
