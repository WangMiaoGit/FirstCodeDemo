package com.example.firstcodedemo.activity

import android.graphics.Color
import android.support.design.widget.AppBarLayout
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.GridView
import android.widget.Toast
import com.example.firstcodedemo.R
import com.example.firstcodedemo.bean.Fruit
import com.example.firstcodedemo.bean.SipnnerData
import com.example.firstcodedemo.ui.FruitAdapter
import com.example.firstcodedemo.ui.banner.GlideImageLoader
import com.example.firstcodedemo.utils.toast
import com.example.firstcodedemo.widget.CustomPartShadowPopupView
import com.example.firstcodedemo.widget.CustomSpinnerAdapter
import com.facebook.imagepipeline.memory.BasePool
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.impl.AttachListPopupView
import com.lxj.xpopup.impl.PartShadowPopupView
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_md.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Thread.currentThread
import java.security.AccessController.getContext
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

import kotlin.random.Random

//MD  效果 +  Rv
class MDActivity : MyAbsActivity() {


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

    //变长的list
//    private  var fruitList: MutableList<Fruit>= mutableListOf<Fruit>()//出错
    private val fruitList = mutableListOf<Fruit>()
    private lateinit var adapter: FruitAdapter

    //    private lateinit var xPopup: AttachListPopupView
    private lateinit var xPopup: BasePopupView

    override fun setContentLayout(): Int = R.layout.activity_md

    override fun initView() {
        setSupportActionBar(md_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_black)

        //状态栏和Toolbar重合  相当于是Activity的颜色
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.statusBarColor = Color.TRANSPARENT


//        md_toolbar.background.alpha=0
        supportActionBar?.setDisplayShowTitleEnabled(false)
        nav_view.setCheckedItem(R.id.nav_call)

        nav_view.setNavigationItemSelectedListener {
            drawer_layout.closeDrawers()
            true
        }

        fab.setOnClickListener {
            Snackbar.make(it, "Snack Bar", Snackbar.LENGTH_SHORT)
                .setAction("Undo") {
                    "Close Toast".toast(this@MDActivity, Toast.LENGTH_SHORT)
                }
                .show()
        }

        initFruits()
        //网格展示  每行三个
        val gridLayoutManager = GridLayoutManager(this, 3)
        recycle_view.layoutManager = gridLayoutManager
        adapter = FruitAdapter(this, fruitList)
        recycle_view.adapter = adapter



        swipe_refresh.setColorSchemeResources(R.color.colorPrimary)
        swipe_refresh.setOnRefreshListener {
            refreshFruit()
        }


        val images = arrayListOf(
            "https://img.tukuppt.com/bg_grid/00/03/30/DSTRZ3a11t.jpg",
            "https://img.tukuppt.com/bg_grid/00/03/31/VKMsak12jv.jpg",
            "https://img.tukuppt.com/bg_grid/00/04/62/KCfzjd8QIp.jpg"
        )


        //设置图片加载器
        category_banner.setImageLoader(GlideImageLoader())
        //设置图片集合
        category_banner.setImages(images)

        category_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)

        //banner设置方法全部调用完毕时最后调用
        category_banner.isAutoPlay(false)
        category_banner.start()


        initAppBarlayout()


        //弹出的pop
//         xPopup = XPopup.Builder(this)
//            .atView(category_banner)  // 依附于所点击的 View，内部会自动判断在上方或者下方显示
//            .asAttachList(
//                listOf("分享", "编辑", "不带 icon").toTypedArray(),
//                intArrayOf(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
//
//
//            ) { _, text ->
//                text.toast(this)
//            }

        val listOf = listOf<SipnnerData>(
            SipnnerData("全部分类"),
            SipnnerData("待报价"),
            SipnnerData("待付款"),
            SipnnerData("待发货")
        )

        val listOf1 = listOf("全部分类", "全部分类", "全部分类", "全部分类",
            "全部分类", "全部分类", "全部分类", "全部分类",
            "全部分类", "全部分类", "全部分类", "全部分类")


        val customSpinnerAdapter = CustomSpinnerAdapter(listOf1)

        xPopup = XPopup.Builder(this)
            .atView(category_banner)  // 依附于所点击的 View，内部会自动判断在上方或者下方显示
            .asCustom(CustomPartShadowPopupView(this, listOf1,customSpinnerAdapter))

        customSpinnerAdapter.setOnItemChildClickListener { adapter, view, position ->
            listOf1[position]
            //改变点击锚点的  tv的文字，重新按选择的内容请求 数据
        }


//        category_banner.setOnClickListener {
//
//            "111".toast(this)
//            xPopup.show()
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        //分支
        when (item?.itemId) {
            android.R.id.home -> drawer_layout.openDrawer(GravityCompat.START)
            R.id.backup -> xPopup.show()
//            R.id.settings -> Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
            R.id.delete -> Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show()
        }
        return true
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
            println(currentThread().name)
            runOnUiThread {
                println(currentThread().name)
                initFruits()
                adapter?.notifyDataSetChanged()
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


    private fun initAppBarlayout() {
        detail_appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
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
                md_toolbar.setBackgroundColor(Color.argb(alpha.toInt(), 255, 255, 255))
            }
        })
    }


}
