package com.example.firstcodedemo.activity

import android.annotation.TargetApi
import android.app.AppOpsManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.firstcodedemo.R
import kotlinx.android.synthetic.main.activity_abstract.*


/**
 *   Name: AbstractActivity
 *   CreateBy: EricRen On 2019/3/18
 *   Copyright:
 *   Language: Kotlin
 *   Description: Activity基类
 */
abstract class AbstractActivity : AppCompatActivity() {

    protected abstract fun initActivity()

    protected abstract fun setContentLayout(): Int

    protected abstract fun initView()


    /**
     * 绑定Context时设置语言（Android5.0+）
     */
//    override fun attachBaseContext(newBase: Context?) {
//        super.attachBaseContext(initLanguage(newBase))
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity()
        setContentView(R.layout.activity_abstract)
        initLayout()
        initView()
    }

    /**
     * 初始化布局
     */
    private fun initLayout() {
        abstract_root.layoutDirection = View.LAYOUT_DIRECTION_LOCALE //布局方向（根据语言启用RTL模式）
        LayoutInflater.from(this).inflate(setContentLayout(), abstract_root) //解析设置的布局，并加载到根布局

        //Toolbar导航按钮默认返回
//        abstract_tool_bar.setNavigationOnClickListener {
//            finish()
//        }
    }

    /**
     * --------------------Toolbar相关方法--------------------
     */

    /**
     * 显示Toolbar
     */
    protected fun showToolbar() {
//        abstract_tool_bar.visibility = View.VISIBLE
    }

    protected fun hideToolbar() {
//        abstract_tool_bar.visibility = View.GONE
    }

    /**
     * 判断是否登录
     */
//    protected fun isLogin(): Boolean = AppPreferences.getInstance().getIsLogin()

    /**
     * --------------------RxBus相关方法--------------------
     */
//    fun subscribeEvent(event: Disposable) {
//        rxEvents.add(event)
//    }

    /**
     * --------------------语言相关方法--------------------
     */
    /**
     * 设置语言主要方法
     * 注意：本地语言和系统语言是可能不一致的，在本地未设置语言的情况下，
     * 默认将系统语言设置为本地语言，之后的初始化全部读取本地语言
     * 初始化语言，主要方法在attachBaseContext中调用
     * 这里的context相关方法必须使用参数中的context对象，否则会造成空指针
     */
//    private fun initLanguage(context: Context?): Context? {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//
//        } else {
//
//        }
//
//        var locale = Locale.getDefault()
//
//        if (AppPreferences.getInstance().getLanguageCode().isEmpty()) { //从未设置语言
//            val language = "${locale.language}_${locale.country}"
//
//            locale = Locale(language)
//            AppPreferences.getInstance().setLanguageCode(language)
//        } else { //已经设置语言
//            locale = Locale(AppPreferences.getInstance().getLanguageCode())
//
//            val language = locale.language
//            AppPreferences.getInstance().setLanguageCode(language)
//        }
//        Locale.setDefault(locale)
//
//        val configuration = context?.resources?.configuration
//        configuration?.setLocale(locale)
//        return context?.createConfigurationContext(Configuration(configuration))
//    }

    /**
     * --------------------软键盘相关方法--------------------
     */
    //隐藏键盘
    protected fun hideKeyboard() {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(
                window.peekDecorView().windowToken,
                0)
    }

    //弹出键盘
    protected fun showKeyboard() {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(
            InputMethodManager.SHOW_FORCED,
            0)
    }

    /**
     * 判断是否隐藏软键盘
     */
    private fun isShouldHideKeyboard(view: View?, event: MotionEvent): Boolean {
        if (view != null && (view is EditText)) {
            val l = intArrayOf(0, 0)
            view.getLocationInWindow(l)

            val left = l[0]
            val top = l[1]
            val bottom = top + view.height
            val right = left + view.width

            return !(event.x > left && event.x < right &&
                    event.y > top && event.y < bottom)
        }
        return false
    }

    /**
     * --------------------开启新界面相关方法--------------------
     */
    /**
     * 普通跳转界面
     */
    protected fun launchActivity(targetActivity: Class<*>) {
        //        startActivity(Intent(this, targetActivity),
        //            ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        startActivity(Intent(this, targetActivity))
    }

    /**
     * 携带参数跳转界面
     * 注意，所有参数放入bundle后进行传输，直接使用intent不太稳定
     * 如果要传输对象，使用Gson转换为String，再放进bundle
     */
    protected fun launchActivity(targetActivity: Class<*>, bundle: Bundle) {
        val intent = Intent(this, targetActivity)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    /**
     * 页面带有返回数据的跳转
     */
    protected fun launchActivityForResult(targetActivity: Class<*>, requestCode: Int) {
        startActivityForResult(Intent(this, targetActivity), requestCode)
    }

    /**
     * 带参数且带有返回数据的跳转
     */
    protected fun launchActivityForResult(targetActivity: Class<*>, bundle: Bundle, requestCode: Int) {
        val intent = Intent(this, targetActivity)
        intent.putExtras(bundle)
        startActivityForResult(intent, requestCode)
    }

    /**
     * --------------------------屏幕适配（重要）--------------------------
     */

    /**
     * 状态栏亮色
     */
    protected fun immersiveStyle() {
        when {
            //Android 9.0+（刘海屏适配）
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.P -> {
                window.statusBarColor = Color.TRANSPARENT
                //TODO Android 9.0+ 刘海屏沉浸式风格适配
                window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                val statusBarView = View(this).apply {
                    setBackgroundColor(Color.TRANSPARENT)
                    layoutParams =
                        ViewGroup
                            .LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                getStatusBarHeight())
                }
                abstract_root.addView(statusBarView, 0) //添加和状态栏一样高的空布局，防止布局被状态栏遮挡
            }

            //Android 6.0+ （亮色状态栏）
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                window.statusBarColor = Color.TRANSPARENT
                window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                val statusBarView = View(this).apply {
                    setBackgroundColor(Color.TRANSPARENT)
                    layoutParams =
                        ViewGroup
                            .LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                getStatusBarHeight())
                }
                abstract_root.addView(statusBarView, 0) //添加和状态栏一样高的空布局，防止布局被状态栏遮挡
            }

            //Android 5.0+
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            }
        }
    }

    protected fun immersiveStatus() {
        //Android 6.0+ （亮色状态栏）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = Color.TRANSPARENT
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            //            val statusBarView = View(this).apply {
            //                setBackgroundColor(Color.TRANSPARENT)
            //                layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight())
            //            }
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        }
    }

    /**
     * 全屏显示，状态栏不可见
     * 当系统为9.0+时，要设置布局是否可以绘制到刘海区域
     */
    protected fun fullScreenStyle() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) { //刘海屏全屏要注意刘海区是否可以绘制
            window.attributes = window.attributes.apply {

                //                layoutInDisplayCutoutMode =
                //                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT
                //                layoutInDisplayCutoutMode =
                //                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER

                layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            }

        } else {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }

    /**
     * 获取刘海区信息
     */
    @TargetApi(Build.VERSION_CODES.P)
    protected fun getNotchParams() {
        val decorView = window.decorView
        decorView.post {
            val windowInsets = decorView.rootWindowInsets
            if (windowInsets != null) {
                val displayCutout = windowInsets.displayCutout
                val rectList = displayCutout?.boundingRects

                if (rectList == null || rectList.size == 0) { //不是刘海屏

                } else { //是刘海屏

                }
            }
        }
    }

    /**
     * 获取状态栏高度
     */
    fun getStatusBarHeight(): Int {
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId)
        }
        return 0
    }

    /**
     * --------------------通知相关--------------------
     */

    /**
     * 显示Loading弹窗
     */
//    protected fun showLoadingDialog() {
//        if (loadingDialog != null) {
//            if (!loadingDialog!!.isShowing) {
//                loadingDialog?.show()
//            }
//        } else {
//            loadingDialog = LoadingDialog(this)
//            loadingDialog?.show()
//        }
//    }

    /**
     * 隐藏Loading弹窗
     */
//    protected fun dismissLoadingDialog() {
//        if (loadingDialog != null) {
//            if (loadingDialog!!.isShowing) {
//                loadingDialog?.dismiss()
//            }
//        } else {
//            loadingDialog = LoadingDialog(this)
//            loadingDialog?.dismiss()
//        }
//    }

    /**
     * 显示通知
     */
    protected fun showNotification(title: String, content: String,
                                   pendingIntent: PendingIntent
    ) {

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
                val channel = NotificationChannel("find_x", "test",
                    NotificationManager.IMPORTANCE_HIGH)
                    .apply {
                        description = "test notification"
                        enableLights(true)
                        lightColor = Color.RED
                        //                        enableVibration(true)
                        setShowBadge(false)
                    }
                notificationManager.createNotificationChannel(channel)

                NotificationCompat.Builder(this, "FirstCodeDemo")
                    .setContentTitle(title)
                    .setContentText(content)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pendingIntent).build()
            }

            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                NotificationCompat.Builder(this, "FirstCodeDemo")
                    .setContentTitle(title)
                    .setContentText(content)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pendingIntent).build()
            }

            else -> {
                NotificationCompat.Builder(this, "FirstCodeDemo")
                    .setContentTitle(title)
                    .setContentText(content)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pendingIntent).build()
            }
        }
        notificationManager.notify(0, notification)
    }

    /**
     * 通知权限是否开启
     */
    protected fun isNotificationEnabled(): Boolean {
        val appOps = getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val pkg = applicationContext.packageName
        val uid = applicationInfo.uid

        try {
            val appOpsClass = Class.forName(AppOpsManager::javaClass.name)
            val method = appOpsClass.getMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE,
                String::class.java)
            val field = appOpsClass.getDeclaredField("OP_POST_NOTIFICATION")
            val value = field.get(Int::class.java).toString().toInt()
            return method.invoke(appOps,
                value,
                uid,
                pkg).toString().toInt() == AppOpsManager.MODE_ALLOWED
        } catch (e: Exception) {
            Log.e(e.message!!,"TTT")
        }
        return false
    }

    /**
     * 跳转开启通知权限
     */
    protected fun gotoNotificationSetting() {
        val intent = Intent().apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            action = "android.settings.APPLICATION_DETAILS_SETTINGS"
            data = Uri.fromParts("package", packageName, null)
            startActivity(intent)
        }
        startActivity(intent)
    }

    /**
     * --------------------触摸事件--------------------
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev!!.action == MotionEvent.ACTION_DOWN) {
            val view = currentFocus
            if (isShouldHideKeyboard(view, ev)) {
                hideKeyboard()
            }
        }
        return super.dispatchTouchEvent(ev)
    }
    /**
     * --------------------数据返回与监听--------------------
     */
    /**
     * 在外部监听onActivityResult方法
     */
    private var activityResultListener: ((requestCode: Int, resultCode: Int, data: Intent?) -> Unit)? = null

    fun setOnActivityResultListener(listener: ((requestCode: Int, resultCode: Int, data: Intent?) -> Unit)) {
        this.activityResultListener = listener
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        activityResultListener?.invoke(requestCode, resultCode, data)
    }


    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    fun getStatusBarHeight(context: Context): Int {
        val resources = context.resources
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        return resources.getDimensionPixelSize(resourceId)
    }
    /**
     * --------------------界面销毁--------------------
     */
    override fun onDestroy() {
//        rxEvents.dispose()
        super.onDestroy()
    }
}