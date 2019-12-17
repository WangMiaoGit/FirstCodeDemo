package com.example.firstcodedemo.ui.selector.imp

import android.content.Context
import android.view.View
import com.example.firstcodedemo.ui.selector.Selector
import android.R.attr.start
import android.animation.ValueAnimator
import android.view.animation.AccelerateDecelerateInterpolator

import android.view.LayoutInflater
import android.graphics.Color.parseColor
import android.support.v4.content.res.TypedArrayUtils.getResourceId
import android.content.res.TypedArray
import android.graphics.Color
import android.util.TypedValue
import android.widget.TextView
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.AttributeSet
import android.widget.ImageView
import com.example.firstcodedemo.R
import android.icu.lang.UCharacter.GraphemeClusterBreak.T





class MySelector : Selector {
    private var tvTitle: TextView? = null
    private var ivIcon: ImageView? = null
    private var ivSelector: ImageView? = null
    private var valueAnimator: ValueAnimator? = null
    private var text: String? = null
    private var iconResId: Int = 0
    private var indicatorResId: Int = 0
    private var textColor: Int = 0
    private var textSize: Int = 0

    constructor(context: Context):super(context){

    }

    constructor(context: Context,attributeSet: AttributeSet) : super(context,attributeSet) {

    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int):  super(context, attrs, defStyleAttr){

    }
    private fun onBindView(
        text: String?,
        iconResId: Int,
        indicatorResId: Int,
        textColor: Int,
        textSize: Int
    ) {
        if (tvTitle != null) {
            tvTitle!!.text = text
            tvTitle!!.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize.toFloat())
            tvTitle!!.setTextColor(textColor)
        }
        if (ivIcon != null) {
            ivIcon!!.setImageResource(iconResId)
        }
        if (ivSelector != null) {
            ivSelector!!.setImageResource(indicatorResId)
            ivSelector!!.setAlpha(0)
        }
    }

    override fun onObtainAttrs(typedArray: TypedArray) {
        text = typedArray.getString(R.styleable.Selector_text)
        iconResId = typedArray.getResourceId(R.styleable.Selector_img, 0)
        indicatorResId = typedArray.getResourceId(R.styleable.Selector_indicator, 0)
        textColor =
            typedArray.getColor(R.styleable.Selector_text_color, Color.parseColor("#FF222222"))
        textSize = typedArray.getInteger(R.styleable.Selector_text_size, 15)
    }

    override fun onCreateView(): View {
        val view = LayoutInflater.from(this.context).inflate(R.layout.age_selector, null)
        tvTitle = view.findViewById(R.id.tv_title)
        ivIcon = view.findViewById(R.id.iv_icon)
        ivSelector = view.findViewById(R.id.iv_selector)
        onBindView(text, iconResId, indicatorResId, textColor, textSize)
        return view
    }

    override fun onSwitchSelected(isSelect: Boolean) {
        if (isSelect) {
            playSelectedAnimation()
        } else {
            playUnselectedAnimation()
        }
    }

    private fun playUnselectedAnimation() {
        if (ivSelector == null) {
            return
        }
        if (valueAnimator != null) {
            valueAnimator!!.reverse()
        }
    }

    private fun playSelectedAnimation() {
        if (ivSelector == null) {
            return
        }
        valueAnimator = ValueAnimator.ofInt(0, 255)
        valueAnimator!!.duration = 400
        valueAnimator!!.interpolator = AccelerateDecelerateInterpolator()
        valueAnimator!!.addUpdateListener { animation -> ivSelector!!.setAlpha(animation.animatedValue as Int) }
        valueAnimator!!.start()
    }
}