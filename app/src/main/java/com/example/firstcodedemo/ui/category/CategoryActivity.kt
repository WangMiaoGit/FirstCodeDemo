package com.example.firstcodedemo.ui.category

import android.content.Context
import android.graphics.Point
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.LayoutInflaterCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.example.firstcodedemo.R
import com.example.firstcodedemo.ui.banner.GlideImageLoader
import com.example.firstcodedemo.ui.category.adapter.Category_left_adapter
import com.example.firstcodedemo.ui.category.adapter.Category_right_adapter
import com.example.firstcodedemo.ui.category.adapter.MyAdapter
import com.youth.banner.Banner
import kotlinx.android.synthetic.main.activity_category_activity.*
import q.rorbin.verticaltablayout.VerticalTabLayout
import q.rorbin.verticaltablayout.widget.TabView
import kotlin.collections.ArrayList
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout


class CategoryActivity : AppCompatActivity() {

    private val categoryBeanList = ArrayList<CategoryBean>()

    private lateinit var parentAdapter: Category_left_adapter
    private lateinit var childAdapter: Category_right_adapter
//    private lateinit var childAdapter: MyAdapter


    private var tabSelectedListener: VerticalTabLayout.OnTabSelectedListener? = null

    private val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    val linearLayoutManager2 = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_activity)



        recycle_left.layoutManager = linearLayoutManager
        recycle_right.layoutManager = linearLayoutManager2

        parentAdapter = Category_left_adapter(categoryBeanList)
        //BRVAH
        childAdapter = Category_right_adapter(categoryBeanList)
        //MyAdapter
//        childAdapter = MyAdapter(this,categoryBeanList)

        initView()
        initData()

        parentAdapter.setOnItemClickListener { _, _, position ->
            parentAdapter.changeSelection(position)
            //带头部
            linearLayoutManager2.scrollToPositionWithOffset(position+1, 0)
            //不带头部
//            linearLayoutManager2.scrollToPositionWithOffset(position, 0)
        }

        //初始化选中状态
//        recycle_left.setScrollingTouchSlop(0)
        parentAdapter.changeSelection(0)

        recycle_left.adapter = parentAdapter
        recycle_left.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        recycle_right.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                val manager = recyclerView.layoutManager as LinearLayoutManager
                // 当不滚动时(看需求，滚动的时候也可以加上)
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {

                    val lastVisibleItem = manager.findFirstVisibleItemPosition()

                    println("当前显示的是哪一个  item$lastVisibleItem")

                    parentAdapter.changeSelection(lastVisibleItem)

//                }
            }
        })
        setHeaderAndFooter()

        recycle_right.adapter = childAdapter

        childAdapter.setOnItemClickListener { adapter, _, position ->
            val any = adapter.data[position]
            println("点击到了")
            if (any is CategoryBean){
                println("点击到了${any.type}")
            }

        }





    }


    private fun initBanner(){
        val images = listOf(
            "https://img.tukuppt.com/bg_grid/00/03/30/DSTRZ3a11t.jpg",
            "https://img.tukuppt.com/bg_grid/00/03/31/VKMsak12jv.jpg",
            "https://img.tukuppt.com/bg_grid/00/04/62/KCfzjd8QIp.jpg",
            "https://img.tukuppt.com/bg_grid/00/03/30/DSTRZ3a11t.jpg",
            "https://img.tukuppt.com/bg_grid/00/04/62/KCfzjd8QIp.jpg"
        )
//        viewpager.setHolderCreator(HolderCreator<NewViewHolder>).create(images)
        val view = LayoutInflater.from(this).inflate(R.layout.recycle_header, null)
//        view.layoutParams= LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT)
         banner = view.findViewById(R.id.banner)
        childAdapter.setHeaderView(view)
        banner.setImages(images)
            .setImageLoader( GlideImageLoader())
            .start()
    }

    lateinit var banner: Banner

    private fun setHeaderAndFooter() {
        val images = arrayListOf(
            "https://img.tukuppt.com/bg_grid/00/03/30/DSTRZ3a11t.jpg",
            "https://img.tukuppt.com/bg_grid/00/03/31/VKMsak12jv.jpg",
            "https://img.tukuppt.com/bg_grid/00/04/62/KCfzjd8QIp.jpg"
        )
////        //设置图片集合
//        new_banner.setImages(images)
//        //设置图片加载器
//        new_banner.setImageLoader(GlideImageLoader())
//        //banner设置方法全部调用完毕时最后调用
//
//        //设置是否允许手动滑动轮播图
//        new_banner.setViewPagerIsScroll(true)
//        //设置是否自动轮播（不设置则默认自动）
//        new_banner.isAutoPlay(true)
//        new_banner.setOnBannerListener {
//            println("点击到了第$it 个")
//        }
//        new_banner.visibility= View.VISIBLE
//        new_banner.start()

//        val images = arrayListOf(
//            "http://pic.962.net/up/2017-7/201773958128749.jpg",
//            "http://b-ssl.duitang.com/uploads/item/201607/04/20160704054727_8Xmni.jpeg",
//            "http://pic.962.net/up/2017-12/2017121124259431530.jpg"
//        )

        Banner(this)


        val view = LayoutInflater.from(this).inflate(R.layout.recycle_header, null)
        val int = getWindowSize()
//        view.layoutParams = LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT)
        println(int)
         banner = view.findViewById<Banner>(R.id.banner)
//        banner.layoutParams = LinearLayoutCompat.LayoutParams(int - 60, 300)
        //设置图片集合
        banner.setImages(images)
        //设置图片加载器
        banner.setImageLoader(GlideImageLoader())
        //banner设置方法全部调用完毕时最后调用

        //设置是否允许手动滑动轮播图
        banner.setViewPagerIsScroll(true)
        //设置是否自动轮播（不设置则默认自动）

        banner.setOnBannerListener {
            println("点击到了第$it 个")
        }



        //        banner.setBannerAnimation(Transformer.ZoomIn)
//        val banner2 = view.findViewById<BGABanner>(R.id.banner_guide_content)
//
//        banner2.setAdapter(object : BGABanner.Adapter<ImageView, String> {
//            override fun fillBannerItem(
//                banner: BGABanner?,
//                itemView: ImageView?,
//                model: String?,
//                position: Int
//            ) {
//                Glide.with(this@CategoryActivity)
//                    .load(model)
//                    .into(itemView!!)
//            }


    //            override fun fillBannerItem( banner:BGABanner,  itemView:ImageView,  model:String,  position:Int) {
    //                Glide.with(this@CategoryActivity)
    //                    .load(model)
    //                    .placeholder(R.drawable.ic_launcher_background)
    //                    .error(R.drawable.ic_launcher_foreground)
    //                    .centerCrop()
    //                    .dontAnimate()
    //                    .into(itemView)
    //            }
//        })
//
//        banner2.setData(images, listOf("提示文字1", "提示文字2", "提示文字3"))

       /* val view3 = LayoutInflater.from(this).inflate(R.layout.recycle_header, null)
        val banner3 = view.findViewById<BannerViewPager<Array<String>,NewViewHolder>>(R.id.banner)




        banner3.showIndicator(true)
                    .setInterval(3000)
//                    .setRoundCorner(R.dimen.banner_corner)
                    .setScrollDuration(1000)
                    .setData(images)
                    .setHolderCreator(  HolderCreator<DataViewHolder>() {

                        override fun  createViewHolder():DataViewHolder {
                            return  DataViewHolder();
                        }
                    })
            .setOnPageClickListener( BannerViewPager.OnPageClickListener() {

                override fun  onPageClick( position:Int) {
                    Toast.makeText(this@CategoryActivity, "点击了图片" + position, Toast.LENGTH_SHORT).show()
                }
            }

                    }).create();*/
        //增加头部的banner
        childAdapter.addHeaderView(view)




        //        val linearLayout = LinearLayout(this)
        //        val layoutParams = LinearLayout.LayoutParams(
        //            LinearLayout.LayoutParams.MATCH_PARENT,
        //            200
        //
        //        )
        //        linearLayout.orientation=LinearLayout.VERTICAL
        //        linearLayout.layoutParams=layoutParams
        val linearLayout = LayoutInflater.from(this).inflate(R.layout.recycle_footer, null)
        childAdapter.addFooterView(linearLayout)


        banner.start()
    }

    private fun initView() {
        tabSelectedListener = object : VerticalTabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabView, position: Int) {

                linearLayoutManager2.scrollToPositionWithOffset(position, 0)

            }

            override fun onTabReselected(tab: TabView, position: Int) {

            }
        }
    }

    private fun initData() {
        val category = CategoryBean.Category()
        category.img = "https://img.tukuppt.com/bg_grid/00/03/30/DSTRZ3a11t.jpg"
        category.title = "服装"

        val category2 = CategoryBean.Category()
        category2.img = "https://img.tukuppt.com/bg_grid/00/03/31/VKMsak12jv.jpg"
        category2.title = "酒水"

        val category3 = CategoryBean.Category()
        category3.img = "https://img.tukuppt.com/bg_grid/00/04/62/KCfzjd8QIp.jpg"
        category3.title = "洗护"

        val categoryBean = CategoryBean()
        val categoryBean2 = CategoryBean()
        val categoryBean3 = CategoryBean()
        val categoryBean4 = CategoryBean()
        val categoryBean5 = CategoryBean()


//        println(category.toString())
//        println(category2.toString())
//        println(category3.toString())

        categoryBean.type = "推荐"
        categoryBean.categoryList = listOf(
            category, category2, category3, category, category2, category3
            , category, category2, category3
        )

        categoryBean2.type = "居家"
        categoryBean2.categoryList = listOf(category2, category, category3, category, category2)

        categoryBean3.type = "餐厨"
        categoryBean3.categoryList =
            listOf(category3, category2, category, category, category2, category3)

        categoryBean4.type = "配件"
        categoryBean4.categoryList = listOf(
            category, category2, category3, category, category2, category3
            , category, category2
        )

        categoryBean5.type = "杂货"
        categoryBean5.categoryList = listOf(
            category, category2, category3, category, category2, category3
            , category, category2
        )

        categoryBeanList.add(categoryBean)
        categoryBeanList.add(categoryBean2)
        categoryBeanList.add(categoryBean3)
        categoryBeanList.add(categoryBean4)
        categoryBeanList.add(categoryBean5)

        parentAdapter.notifyDataSetChanged()
        childAdapter.notifyDataSetChanged()


    }



    //获取屏幕宽高
    private fun getWindowSize():Int {
        val defaultDisplay = windowManager.defaultDisplay
        val point = Point()
        defaultDisplay.getSize(point)
        val x = point.x
        val y = point.y

        val wm =  this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm =  DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)


        val width = dm.widthPixels         // 屏幕宽度（像素）
        val height = dm.heightPixels       // 屏幕高度（像素）
        val density = dm.density         // 屏幕密度（0.75 / 1.0 / 1.5）
        val densityDpi = dm.densityDpi     // 屏幕密度dpi（120 / 160 / 240）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        val screenWidth = (width / density).toInt()  // 屏幕宽度(dp)
        val screenHeight = (height / density).toInt()// 屏幕高度(dp)



        Log.d("h_bl", "屏幕宽度（像素）：$width")
        Log.d("h_bl", "屏幕高度（像素）：$height")
        Log.d("h_bl", "屏幕密度（0.75 / 1.0 / 1.5）：$density")
        Log.d("h_bl", "屏幕密度dpi（120 / 160 / 240）：$densityDpi")
        Log.d("h_bl", "屏幕宽度（dp）：$screenWidth")
        Log.d("h_bl", "屏幕高度（dp）：$screenHeight")


        return screenWidth
    }

}
