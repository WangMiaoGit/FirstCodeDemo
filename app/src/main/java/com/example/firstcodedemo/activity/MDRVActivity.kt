package com.example.firstcodedemo.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import com.example.firstcodedemo.R
import com.example.firstcodedemo.bean.Fruit
import com.example.firstcodedemo.ui.BRVAH_FruitAdapter
import com.example.firstcodedemo.ui.FruitAdapter
import com.example.firstcodedemo.utils.toast
import kotlinx.android.synthetic.main.activity_md.*
import kotlinx.android.synthetic.main.recycle_header.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Thread.currentThread
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

import kotlin.random.Random
import android.R.attr.start
import com.example.firstcodedemo.ui.banner.GlideImageLoader
import android.R.attr.banner
import com.youth.banner.Banner
import android.support.v4.app.SupportActivity
import android.support.v4.app.SupportActivity.ExtraData
import android.support.v4.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.LayoutInflater
import com.youth.banner.Transformer


class MDRVActivity : MyAbsActivity() {

    //定长的list
    private val fruits = listOf(
        Fruit("Apple", R.mipmap.ic_launcher),
        Fruit("Orange", R.mipmap.ic_launcher),
        Fruit("Banana", R.mipmap.ic_launcher),
        Fruit("WaterMelon", R.mipmap.ic_launcher),
        Fruit("Pear", R.mipmap.ic_launcher),
        Fruit("Grape", R.mipmap.ic_launcher),
        Fruit("Pineapple", R.mipmap.ic_launcher),
        Fruit("Strawberry", R.mipmap.ic_launcher),
        Fruit("Cherry", R.mipmap.ic_launcher),
        Fruit("Mango", R.mipmap.ic_launcher)
    )

    private val fruitAdd = Fruit("add", R.drawable.ic_add_black)
    //变长的list
//    private  var fruitList: MutableList<Fruit>= mutableListOf<Fruit>()//出错
    private val fruitList = mutableListOf<Fruit>()
    //    private lateinit var adapter: FruitAdapter
    private lateinit var adapter: BRVAH_FruitAdapter

    override fun setContentLayout(): Int = R.layout.activity_md

    override fun initView() {
        setSupportActionBar(md_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_black)

        //状态栏和Toolbar重合  相当于是Activity的颜色
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.statusBarColor = Color.TRANSPARENT


        nav_view.setCheckedItem(R.id.nav_call)

        nav_view.setNavigationItemSelectedListener {
            drawer_layout.closeDrawers()
            true
        }

        fab.setOnClickListener {
            Snackbar.make(it, "Snack Bar", Snackbar.LENGTH_SHORT)
                .setAction("Undo") {
                    "Close Toast".toast(this@MDRVActivity, Toast.LENGTH_SHORT)
                }
                .show()
        }

        initFruits()
        //网格展示  每行三个
        val gridLayoutManager = GridLayoutManager(this, 3)
        recycle_view.layoutManager = gridLayoutManager
//        adapter = FruitAdapter(this, fruitList)


        //自定义  最后一个点击回调
//        adapter.setAddListener(object : FruitAdapter.AddListener {
//            override fun addFruit() {
//                fruitList[fruitList.size-1]=Fruit("Mango", R.mipmap.ic_launcher)
//                fruitList.add(fruitAdd)
//
//                adapter.notifyDataSetChanged()
//            }
//        })


        adapter = BRVAH_FruitAdapter(fruitList)
        adapter.setOnItemClickListener { adapter, _, position ->
            val item = adapter.getItem(position) as Fruit

            if (position == fruitList.size - 1) {
                fruitList[fruitList.size - 1] = Fruit("Mango", R.mipmap.ic_launcher)
                fruitList.add(fruitAdd)
                adapter.notifyDataSetChanged()
            } else {

                val intent = Intent(this, FruitActivity::class.java)
                intent.putExtra("fruitName", item.name)
                intent.putExtra("fruitId", item.imgId)
                this.startActivity(intent)
            }
        }


        recycle_view.adapter = adapter


        val images = arrayListOf(
            "https://img.tukuppt.com/bg_grid/00/03/30/DSTRZ3a11t.jpg",
            "https://img.tukuppt.com/bg_grid/00/03/31/VKMsak12jv.jpg",
            "https://img.tukuppt.com/bg_grid/00/04/62/KCfzjd8QIp.jpg"
        )

        val view = LayoutInflater.from(this).inflate(R.layout.recycle_header, null)
        val banner = view.findViewById<Banner>(R.id.banner)
        banner.isAutoPlay(true)
        //设置图片加载器
        banner.setImageLoader(GlideImageLoader())
        //设置图片集合
        banner.setImages(images)
        //banner设置方法全部调用完毕时最后调用
        banner.start()
//        banner.setBannerAnimation(Transformer.Stack)
//        banner.setBannerAnimation(Transformer.ZoomIn)

        //增加头部的banner
        adapter.addHeaderView(view)

        swipe_refresh.setColorSchemeResources(R.color.colorPrimary)
        swipe_refresh.setOnRefreshListener {
            refreshFruit()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        //分支
        when (item?.itemId) {
            android.R.id.home -> drawer_layout.openDrawer(GravityCompat.START)
            R.id.backup -> Toast.makeText(this, "Backup", Toast.LENGTH_SHORT).show()
            R.id.settings -> Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
            R.id.delete -> Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    //随机生成  fruit
    private fun initFruits() {
        println("初始化Fruit")
        fruitList.clear()
        //循环
        for (i in fruits) {
            fruitList.add(i)
        }

        fruitList.add(fruitAdd)

    }

    private fun refreshFruit() {

        Thread(Runnable {
            Thread.sleep(3000)
            println(currentThread().name)
            runOnUiThread {
                println(currentThread().name)
                initFruits()
                adapter.notifyDataSetChanged()
                swipe_refresh.isRefreshing = false
            }
        }).start()


//        GlobalScope.launch {
//            delay(3000)
//        }
//        initFruits()
//        adapter.notifyDataSetChanged()
//        swipe_refresh.isRefreshing = false

    }


}
