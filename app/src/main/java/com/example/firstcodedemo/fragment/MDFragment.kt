package com.example.firstcodedemo.fragment


import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.firstcodedemo.R
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.example.firstcodedemo.bean.Fruit
import com.example.firstcodedemo.ui.FruitAdapter
import com.example.firstcodedemo.ui.banner.GlideImageLoader
import kotlinx.android.synthetic.main.activity_md.*
import kotlinx.android.synthetic.main.fragment_md.*


/**
 * A simple [Fragment] subclass.
 */
class MDFragment : MyAbsFragment() {

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
        Fruit("Mango", R.mipmap.ic_launcher),
        Fruit("add", R.drawable.ic_add_black)
    )
    private val fruitList = mutableListOf<Fruit>()
    private lateinit var adapter: FruitAdapter
    companion object {
        fun newInstance(): MDFragment {
            return MDFragment().apply {
                arguments = Bundle()
            }
        }

        fun newInstance(args: Bundle): MDFragment {
            return MDFragment().apply {
                arguments = Bundle(args)
            }
        }
    }


    override fun setContentLayout(): Int= R.layout.fragment_md

    override fun initFragment() {

    }

    override fun initView() {
        (activity as AppCompatActivity).setSupportActionBar(fragment_md_toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_black)

        initFruits()
        initAppBarlayout()
        initRecycle()
        initBanner()
    }

    private fun initRecycle() {
        //网格展示  每行三个
        val gridLayoutManager = GridLayoutManager(context, 3)
        fragment_recycle_view.layoutManager = gridLayoutManager
        adapter = FruitAdapter(context!!, fruitList)
        fragment_recycle_view.adapter = adapter

        fragment_swipe_refresh.setColorSchemeResources(R.color.colorPrimary)
        fragment_swipe_refresh.setOnRefreshListener {
            refreshFruit()
        }
    }

    private fun initBanner() {
        val images = arrayListOf(
            "https://img.tukuppt.com/bg_grid/00/03/30/DSTRZ3a11t.jpg",
            "https://img.tukuppt.com/bg_grid/00/03/31/VKMsak12jv.jpg",
            "https://img.tukuppt.com/bg_grid/00/04/62/KCfzjd8QIp.jpg"
        )
        //设置图片加载器
        fragment_category_banner.setImageLoader(GlideImageLoader())
        //设置图片集合
        fragment_category_banner.setImages(images)
        //banner设置方法全部调用完毕时最后调用
        fragment_category_banner.isAutoPlay(false)
        fragment_category_banner.start()
    }

    private fun initAppBarlayout() {
        fragment_detail_appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val percent =
                java.lang.Float.valueOf(Math.abs(verticalOffset).toFloat()) / java.lang.Float.valueOf(
                    appBarLayout.totalScrollRange.toFloat()
                )
            //第一种
            val toolbarHeight = appBarLayout.totalScrollRange
            val dy = Math.abs(verticalOffset)
            if (dy <= toolbarHeight) {
                val scale = dy.toFloat() / toolbarHeight
                val alpha = scale * 255
                fragment_md_toolbar.setBackgroundColor(Color.argb(alpha.toInt(), 255, 255, 255))
            }
        })
    }


    //随机生成  fruit
    private fun initFruits() {
        println("初始化Fruit")
        fruitList.clear()
        //循环
        for (i in 1..20) {
            //随机数 fruits.indices  list的index 的range
            val random = (fruits.indices).random()
            fruitList.add(fruits[random])

//            val random1 = java.util.Random()
//            val index = random1.nextInt(fruits.size)
//            fruitList.add(fruits[index])
        }
        println(fruitList)
    }

    private fun refreshFruit() {

        Thread(Runnable {
            Thread.sleep(3000)
            println(Thread.currentThread().name)
            activity?.runOnUiThread {
                println(Thread.currentThread().name)
                initFruits()
                adapter.notifyDataSetChanged()
                swipe_refresh.isRefreshing = false
            }
        }).start()


    }

}
