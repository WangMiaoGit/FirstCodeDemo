package com.example.firstcodedemo.activity

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.firstcodedemo.R
import com.just.agentweb.AgentWeb
import com.scwang.smartrefresh.header.MaterialHeader
import kotlinx.android.synthetic.main.activity_web_test.*

class WebTestActivity : MyAbsActivity() {

    private lateinit var agentWeb: AgentWeb
    //默认搜索条颜色
    private var backgroundColor = Color.WHITE
    //当前搜索条颜色
    private var currentBgColor = 0
    //顶部图标颜色
    private var toggleScrollTop = true


    private val webUrl = "https://www.baidu.com"
    override fun setContentLayout(): Int = R.layout.activity_web_test

    override fun initView() {
        //初始化网页
        agentWeb = AgentWeb.with(this)
            .setAgentWebParent(f_main_fl_web_new, ViewGroup.LayoutParams(-1, -1))
            .useDefaultIndicator()
//            .setWebView(CustomWebView(context!!)) //设置自定义webView
            .setWebViewClient(object : WebViewClient() {
                //重定向7.0+
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                    }
                    return true //返回true代表拦截，如果需要在本页跳转，需要调用webView.loadUrl
                }

                //重定向6.0-
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {

                    }
                    return true
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
//                    f_main_refresh_new.finishRefresh()
                }

                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    super.onReceivedError(view, request, error)
//                    f_main_refresh_new.finishRefresh(false)
                }
            })
            .createAgentWeb()
            .ready()
            .go(webUrl)


        agentWeb.webCreator.webView.setOnTouchListener { v, ev ->
            println("webView触摸")

            (v as WebView).requestDisallowInterceptTouchEvent(false)
            false

        }


        //初始化Refresh
//        f_main_refresh_new.setEnableLoadMore(false)
//        f_main_refresh_new.setEnableHeaderTranslationContent(true) //内容跟随下拉移动
//        f_main_refresh_new.setRefreshHeader(MaterialHeader(this))
//        f_main_refresh_new.setOnRefreshListener {
//            agentWeb.urlLoader.loadUrl(webUrl)
//        }


//        f_main_scroll.setOnScrollChangeListener { nestedScrollView: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
//
//
//            val i1 = f_main_scroll.getChildAt(0).measuredHeight - f_main_scroll.measuredHeight
//
//            println("滑动的总距离+$i1")
//            println("滑动距离+$scrollY")
//        }
    }

}
