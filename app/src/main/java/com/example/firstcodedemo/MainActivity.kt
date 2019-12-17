package com.example.firstcodedemo


import android.annotation.SuppressLint
import android.app.Notification
import android.graphics.Color
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.firstcodedemo.activity.*
import com.example.firstcodedemo.ui.MusicUi
import com.example.firstcodedemo.ui.captcha.ImgCapthcaActivity
import com.example.firstcodedemo.ui.category.CategoryActivity
import com.example.firstcodedemo.utils.GlideRoundedCornersTransform
import kotlinx.android.synthetic.main.activity_main.*
import android.R.attr.banner
import android.R.attr.start
import com.example.firstcodedemo.ui.banner.GlideImageLoader
import android.support.v4.app.SupportActivity
import android.support.v4.app.SupportActivity.ExtraData
import android.support.v4.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.example.firstcodedemo.ui.selector.SelectorActivity


class MainActivity : MyAbsActivity() {

    override fun setContentLayout(): Int = R.layout.activity_main

    @SuppressLint("ResourceAsColor")
    override fun initView() {
        main_toolbar.title = "123"

        setSupportActionBar(main_toolbar)

        //取消状态栏
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        //状态栏上浮在Toolbar上方  半透明灰色
//        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        //状态栏和Toolbar重合  相当于是Activity的颜色
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.statusBarColor = Color.TRANSPARENT

        val images = arrayListOf(
            "https://img.tukuppt.com/bg_grid/00/03/30/DSTRZ3a11t.jpg",
            "https://img.tukuppt.com/bg_grid/00/03/31/VKMsak12jv.jpg",
            "https://img.tukuppt.com/bg_grid/00/04/62/KCfzjd8QIp.jpg"
        )
        //设置图片加载器
        te_banner.setImageLoader(GlideImageLoader())
        //设置图片集合
        te_banner.setImages(images)
        //banner设置方法全部调用完毕时最后调用
        te_banner.start()



        btn_open.setOnClickListener {
            launchActivity(ShowFragmentActivity::class.java)
        }
        btn_open_md.setOnClickListener {
//            launchActivity(CategoryActivity::class.java)
//            launchActivity(MDRVActivity::class.java)
            launchActivity(MDActivity::class.java)
        }
        btn_open_musicUi.setOnClickListener {
            musicUi.invalidate()
        }
        btn_open_view.setOnClickListener{
            launchActivity(PaintActivity::class.java)
        }
        btn_open_fragment_activity.setOnClickListener{
            launchActivity(FragmentActivity::class.java)
//            launchActivity(SelectorActivity::class.java)
        }


        val myOptions = RequestOptions().optionalTransform(
            GlideRoundedCornersTransform(
                5f
                , GlideRoundedCornersTransform.CornerType.ALL
            )
        )
        Glide.with(this).load(R.mipmap.ic_launcher)
            .apply(myOptions).into(img_main)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.backup -> {
                Toast.makeText(this, "Backup", Toast.LENGTH_SHORT).show()
                launchActivity(ImgCapthcaActivity::class.java)
            }
//            R.id.settings -> Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
            R.id.delete -> Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show()
        }
        return true
    }
}
