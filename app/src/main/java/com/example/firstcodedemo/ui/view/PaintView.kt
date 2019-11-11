package com.example.firstcodedemo.ui.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.graphics.RectF
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.icu.lang.UCharacter.GraphemeClusterBreak.T











class PaintView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
//        paintPointView(canvas)
//        paintLineView(canvas)
//        paintCircleView(canvas)
//        paintOvalCView(canvas)
//        paintRectView(canvas)
//        paintRoundRect(canvas)
        paintPath(canvas)
    }

    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        super.layout(l, t, r, b)
    }


    private fun paintPointView(canvas: Canvas?) {
        val paint = Paint()

        paint.strokeWidth = 5.0.toFloat()
        canvas!!.drawPoint(50f, 50f, paint)
        canvas.translate(0f, 100f)

        val pst = floatArrayOf(20f, 20f, 40f, 40f, 60f, 60f)
        paint.color = Color.RED
        canvas.drawPoints(pst, paint)
        canvas.translate(0f, 100f)
        //跳过前两个元素
        canvas.drawPoints(pst, 2, 4, paint)
    }

    private fun paintLineView(canvas: Canvas?){
        val paint = Paint()
        paint.color=Color.RED
        //public void drawLine(float startX, float startY, float stopX, float stopY,@NonNull Paint paint)
        canvas!!.drawLine(0f, 0f, 100f, 100f, paint)
        canvas.translate(0f, 110f)

        //画一组线
        val pts = floatArrayOf(
            0f, 0f, 100f, 0f, 100f, 0f, 100f, 100f, 100f, 100f, 0f, 100f, 1f, 100f, 1f, 0f
        )
        //线宽
        paint.strokeWidth = 2.0.toFloat()
        //pts : 绘制直线的端点数组，每条直线占用4个数据。
        //public void drawLines(@Size(min=4,multiple=2) @NonNull float[] pts, @NonNull Paint paint)
//        canvas.drawLines(pts, paint);
        //offset : 指定跳过的数据个数
        //count : 指定取出数据的个数
        //public void drawLines(@Size(min=4,multiple=2) float[] pts, int offset, int count, Paint paint)
        canvas.drawLines(pts, 4, 12, paint)

    }

    private fun paintCircleView(canvas: Canvas?) {
        val paint = Paint()
        paint.color=Color.YELLOW
        paint.strokeWidth=3.0f

        //x:起点      y:起点        r:半径
        canvas!!.drawCircle(100f,100f,50f,paint)


    }

    //椭圆
    private fun paintOvalCView(canvas: Canvas?){
        val paint = Paint()
        paint.color=Color.YELLOW
        paint.strokeWidth=3.0f
        val rectF = RectF(0f, 0f, 150f, 100f)
        //oval : 外接矩形
        //public void drawOval(@NonNull RectF oval, @NonNull Paint paint)
        canvas!!.drawOval(rectF, paint)
    }

    private fun paintRectView(canvas: Canvas?){
        val paint = Paint()
        paint.color=Color.YELLOW

        canvas!!.drawRect(0f, 0f, 100f, 100f, paint)
        canvas.translate(0f, 110f)

        canvas.drawRect(RectF(0f, 0f, 100f, 100f), paint)
        canvas.translate(0f, 110f)

        canvas.drawRect(Rect(0, 0, 100, 100), paint)
    }

    //画圆角矩形
    private fun paintRoundRect(canvas: Canvas?) {
        val paint = Paint()
        paint.color = Color.BLUE
        //rect : 要绘制的矩形
        //rx : x轴方向的弧度
        //ry : y轴方向上的弧度
        //public void drawRoundRect(@NonNull RectF rect, float rx, float ry, @NonNull Paint paint)
        canvas!!.drawRoundRect(RectF(100f, 100f, 400f, 400f), 30f, 30f, paint)
        //其他重载方法类似
    }

    private fun paintPath(canvas: Canvas?) {
        val paint = Paint()
        val path = Path()                     //Path对象
        path.moveTo(10f, 50f)                           //起始点
        path.lineTo(50f, 50f)                           //连线到下一点
        path.lineTo(10f, 150f)                      //连线到下一点
        path.lineTo(50f, 100f)                      //连线到下一点
        path.lineTo(50f, 100f)                      //连线到下一点
        paint.color = Color.RED
        canvas!!.drawPath(path, paint)                   //绘制任意多边形
    }
}