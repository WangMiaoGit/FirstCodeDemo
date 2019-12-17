package com.example.firstcodedemo.ui.selector

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.content.res.TypedArray
import android.view.ViewGroup

import android.util.AttributeSet
import com.example.firstcodedemo.R


abstract class Selector : FrameLayout, View.OnClickListener {
    /**
     * the unique tag for a selector
     */
    private var tag: String? = null
    /**
     * the tag indicates which group this selector belongs to,
     * set the same group tag for selectors which want single choice mode
     */
    private var groupTag: String? = null
    /**
     * the group which this Selector belongs to
     */
    private var selectorGroup: SelectorGroup? = null

    constructor(context: Context) :super(context) {
        initView(context, null)
    }

    constructor (context: Context, attrs: AttributeSet) : super(context,attrs) {

        initView(context, attrs)
    }

    constructor (context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs,defStyleAttr) {

        initView(context, attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet?) {
        //read declared attributes
        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.Selector)
            val tagResId = typedArray.getResourceId(R.styleable.Selector_tag, 0)
            tag = context.getString(tagResId)
            onObtainAttrs(typedArray)
            typedArray.recycle()
        } else {
            tag = "default tag"
        }
        //inflate views
        val view = onCreateView()
        val params = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        this.addView(view, params)
        this.setOnClickListener(this)

    }

    open fun onObtainAttrs(typedArray: TypedArray) {}

    /**
     * add this Selector into a SelectorGroup
     *
     * @param selectorGroup
     * @return
     */
    fun setGroup(groupTag: String, selectorGroup: SelectorGroup): Selector {
        this.selectorGroup = selectorGroup
        this.groupTag = groupTag
        return this
    }

    fun getGroupTag(): String? {
        return groupTag
    }

    /**
     * design how the selector looks like
     *
     * @return
     */
    protected abstract fun onCreateView(): View

    fun getSelectorTag(): String? {
        return tag
    }

    fun setSelectorTag(tag: String) {
        this.tag = tag
    }

    override fun setSelected(selected: Boolean) {
        val isPreSelected = isSelected
        super.setSelected(selected)
        if (isPreSelected != selected) {
            onSwitchSelected(selected)
        }
    }

    override fun onClick(v: View) {
        //deliver the click event to the SelectorGroup
        if (selectorGroup != null) {
            selectorGroup!!.onSelectorClick(this)
        }
    }

    /**
     * it will be invoked when select state changes
     *
     * @param isSelect
     */
    protected abstract fun onSwitchSelected(isSelect: Boolean)

}