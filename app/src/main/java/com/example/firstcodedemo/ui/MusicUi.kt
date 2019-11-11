package com.example.firstcodedemo.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import kotlin.random.Random


//音乐矩形  自定义view
class MusicUi : View {
    //矩形个数
    private var mRectCount = 12

    private var mWidth = 0
    //矩形宽度
    private var mRectWidth = 0
    //矩形高度
    private var mRectHeight = 0
    private var mPaint = Paint()
    //矩形之间的间距
    private val offset = 5
    //矩形高度的渐变色
    private lateinit var mLinearGradient: LinearGradient

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {

        mPaint.apply {
            this.color = Color.BLUE
            this.style = Paint.Style.FILL
        }


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        for (i in 0 until mRectCount) {

            val random = Math.random()
            val currentHeight = (mRectHeight * random).toFloat()
            canvas?.drawRect(
                (mWidth * 0.4 / 2 + mRectWidth * i + offset).toFloat(),
                currentHeight,
                (mWidth * 0.4 / 2 + mRectWidth * (i + 1)).toFloat(),
                mRectHeight.toFloat(),
                mPaint
            )
//                postInvalidateDelayed(300)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = width
        mRectHeight = height
        mRectWidth = ((mWidth * 0.6 / mRectCount).toInt())

        mLinearGradient = LinearGradient(
            0f,
            0f,
            mRectWidth.toFloat(),
            mRectHeight.toFloat(),
            Color.YELLOW,
            Color.BLUE,
            Shader.TileMode.CLAMP
        )
        mPaint.shader = mLinearGradient
    }
}