package com.example.firstcodedemo.ui.selector.imp

import android.content.Context
import android.view.View
import com.example.firstcodedemo.ui.selector.Selector
import android.R.attr.start
import android.support.design.animation.AnimatorSetCompat.playTogether
import android.support.v4.view.ViewCompat.setScaleY
import android.support.v4.view.ViewCompat.setScaleX
import android.animation.ValueAnimator
import android.view.animation.AnticipateOvershootInterpolator
import android.view.animation.AccelerateDecelerateInterpolator
import android.animation.AnimatorSet

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


class OrderSelector: Selector {
    private var tvTitle: TextView? = null
    private var ivIcon: ImageView? = null
    private var ivSelector: ImageView? = null
    private var alphaAnimator: ValueAnimator? = null
    private var scaleAnimator: ValueAnimator? = null
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
        val view = LayoutInflater.from(this.context).inflate(R.layout.order_selector, null)
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
        if (alphaAnimator != null) {
            alphaAnimator!!.reverse()
        }
    }

    private fun playSelectedAnimation() {
        if (ivSelector == null) {
            return
        }
        val set = AnimatorSet()
        alphaAnimator = ValueAnimator.ofInt(0, 255)
        alphaAnimator!!.duration = 200
        alphaAnimator!!.interpolator = AccelerateDecelerateInterpolator()
        alphaAnimator!!.addUpdateListener { animation -> ivSelector!!.setAlpha(animation.animatedValue as Int) }

        scaleAnimator = ValueAnimator.ofFloat(1f, 1.3f, 1f)
        scaleAnimator!!.duration = 500
        scaleAnimator!!.interpolator = AnticipateOvershootInterpolator()
        scaleAnimator!!.addUpdateListener { animation ->
            ivSelector!!.setScaleX(animation.animatedValue as Float)
            ivSelector!!.setScaleY(animation.animatedValue as Float)
        }
        set.playTogether(alphaAnimator, scaleAnimator)
        set.start()
    }

}