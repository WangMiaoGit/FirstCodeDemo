package com.example.firstcodedemo.ui

import android.R
import android.content.Context
import android.graphics.*
import android.support.annotation.Nullable
import android.util.AttributeSet
import android.widget.TextView
import android.graphics.LinearGradient


open class UITextView(context: Context, @Nullable attrs: AttributeSet?) : TextView(context, attrs) {

    private val paint = Paint()
    private val paint2 = Paint()


    private var mViewWidth = 0
    private var mTranslate = 0
    private var mPaint: Paint? = null
    private lateinit var mLinearGradient: LinearGradient
    private var mGradientMatrix: Matrix? = null
    private val mAnimating = true
//    constructor(context: Context) : super(context) {
//        intiPaint()
//    }


    private fun intiPaint() {
        paint.apply {
            this.color = resources.getColor(R.color.holo_blue_bright)
            this.style = Paint.Style.FILL
        }
        paint2.apply {
            this.color = Color.YELLOW
            this.style = Paint.Style.FILL
        }
    }

    init {
        intiPaint()
    }

//    constructor(
//        context: Context, @Nullable attrs: AttributeSet?,
//        defStyleAttr: Int
//    ) : super(context, attrs, defStyleAttr) {
//        intiPaint()
//    }


/*    override fun onDraw(canvas: Canvas?) {


        //绘制外侧矩形
        canvas?.drawRect(
            0f, 0f,
            measuredWidth.toFloat(),
            measuredHeight.toFloat(),
            paint
        )
        //绘制内层矩形
        canvas?.drawRect(
            10f, 10f,
            (measuredWidth - 10).toFloat(),
            (measuredHeight - 10).toFloat(),
            paint2
        )

        canvas?.save()
        canvas?.translate(10f, 0f)


        super.onDraw(canvas)

        canvas?.restore()

    }*/


    override fun onDraw(canvas: Canvas?) {


        super.onDraw(canvas)

        if (mAnimating && mGradientMatrix != null) {
            mTranslate += mViewWidth / 8
            if (mTranslate >= mViewWidth) {
                mTranslate = -mViewWidth

            }

            mGradientMatrix?.setTranslate(mTranslate.toFloat(), 0f)


            mLinearGradient.setLocalMatrix(mGradientMatrix)
            postInvalidateDelayed(300)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (mViewWidth == 0) {
            mViewWidth = measuredWidth
            if (mViewWidth > 0) {
                mPaint = getPaint()
                //线性渐变
                mLinearGradient = LinearGradient(
                    0f,
                    0f,
                    mViewWidth.toFloat(),
                    0f,
                    intArrayOf(Color.BLUE, Color.RED, Color.BLUE),
                    null,
                    Shader.TileMode.CLAMP
                )
                mPaint?.shader = mLinearGradient

                mGradientMatrix = Matrix()
            }
        }

    }

}