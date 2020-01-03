package com.example.firstcodedemo.widget

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.example.firstcodedemo.R
import kotlinx.android.synthetic.main.view_title_toolbar.view.*

/**
 * Name: TitleToolbar
 * CreateBy: EricRen On 2019/4/15
 * Copyright:
 * Language: Kotlin
 * Description: 自定义Toolbar
 *
 * -----------------------------------------------------------
 * |NavIcon|NavText|       Title           |MenuText|MenuIcon|
 * -----------------------------------------------------------
 */
class TitleToolbar : FrameLayout {

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context, attrs)
    }

    private fun initView(context: Context) {
        initView(context, null)
    }

    private fun initView(context: Context, attrs: AttributeSet?) {
        //加载View布局
        LayoutInflater.from(context).inflate(R.layout.view_title_toolbar, this)

        //加载自定义属性
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleToolbar)

        //初始化导航图标
        val navIconId = typedArray.getResourceId(R.styleable.TitleToolbar_nav_icon, 0)
        if (navIconId != 0) {
            setNavigationIcon(navIconId)
        }

        //初始化导航文本(id)
        val navTextId = typedArray.getResourceId(R.styleable.TitleToolbar_nav_text, 0)
        if (navTextId != 0) {
            setNavigationText(navIconId)
        }

        //初始化导航文本(string)
        val navText = typedArray.getString(R.styleable.TitleToolbar_nav_text)
        if (!TextUtils.isEmpty(navText)) {
            setNavigationText(navText)
        }

        typedArray.recycle()
    }

    /**
     * 设置标题
     */
    fun setTitle(resId: Int) {
        v_toolbar_title.setText(resId)
        v_toolbar_title.visibility = View.VISIBLE
    }
    /**
     * 设置标题  带颜色修改
     */
    fun setTitle(resId: Int,color:Int) {
        v_toolbar_title.setText(resId)
        v_toolbar_title.setTextColor(color)
        v_toolbar_title.visibility = View.VISIBLE
    }

    fun setTitle(title: CharSequence?) {
        v_toolbar_title.text = title
        v_toolbar_title.visibility = View.VISIBLE
    }

    /**
     * 设置导航图标
     */
    fun setNavigationIcon(resId: Int) {
        v_toolbar_nav_icon.setImageResource(resId)
        v_toolbar_nav_icon.visibility = View.VISIBLE
    }

    fun setNavigationIcon(icon: Drawable?) {
        v_toolbar_nav_icon.setImageDrawable(icon)
        v_toolbar_nav_icon.visibility = View.VISIBLE
    }

    /**
     * 设置导航文本
     */
    fun setNavigationText(resId: Int) {
        v_toolbar_nav_text.setText(resId)
        v_toolbar_nav_text.visibility = View.VISIBLE
    }

    fun setNavigationText(text: CharSequence?) {
        v_toolbar_nav_text.text = text
        v_toolbar_nav_text.visibility = View.VISIBLE
    }

    /**
     * 导航按钮点击事件和回调
     */
    private var navigationOnClickListener: ((view: View) -> Unit)? = null

    fun setNavigationOnClickListener(navigationOnClickListener: (view: View) -> Unit) {
        this.navigationOnClickListener = navigationOnClickListener
        v_toolbar_ll_nav.setOnClickListener {
            this.navigationOnClickListener?.invoke(it)
        }
    }

    /**
     * 设置菜单文本
     */
    fun setMenuText(resId: Int) {
        v_toolbar_menu_text.setText(resId)
        v_toolbar_menu_text.visibility = View.VISIBLE
        v_toolbar_menu_img.visibility = View.GONE
    }

    fun setMenuText(charSequence: CharSequence) {
        v_toolbar_menu_text.text = charSequence
    }

    /**
     * 设置菜单图标，文本和图片优先显示图标
     */
    fun setMenuIcon(resId: Int) {
        v_toolbar_menu_img.setImageResource(resId)
        v_toolbar_menu_img.visibility = View.VISIBLE
        v_toolbar_menu_text.visibility = View.GONE
    }

    /**
     * 菜单点击事件和回调
     */
    private var menuOnClickListener: ((view: View) -> Unit)? = null

    fun setMenuOnClickListener(menuOnClickListener: ((view: View) -> Unit)) {
        this.menuOnClickListener = menuOnClickListener
        if (v_toolbar_menu_text.visibility == View.VISIBLE) {
            v_toolbar_menu_text.setOnClickListener {
                this.menuOnClickListener?.invoke(it)
            }
        }

        if (v_toolbar_menu_img.visibility == View.VISIBLE) {
            v_toolbar_menu_img.setOnClickListener {
                this.menuOnClickListener?.invoke(it)
            }
        }
    }
}
