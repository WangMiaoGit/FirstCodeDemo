package com.example.firstcodedemo.utils

import android.graphics.*
import android.provider.SyncStateContract.Helpers.update
import com.bumptech.glide.load.Key
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.firstcodedemo.BuildConfig
import java.security.MessageDigest


class GlideRoundedCornersTransform(
    private val mRadius: Float,
    private val mCornerType: CornerType
) : CenterCrop() {

    /** 待处理的圆角枚举 */
    enum class CornerType {
        ALL,
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT,
        TOP, BOTTOM, LEFT, RIGHT,
        TOP_LEFT_BOTTOM_RIGHT,
        TOP_RIGHT_BOTTOM_LEFT,
        TOP_LEFT_TOP_RIGHT_BOTTOM_RIGHT,
        TOP_RIGHT_BOTTOM_RIGHT_BOTTOM_LEFT,
        TOP_LEFT_TOP_RIGHT
    }

    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap? {
        val transform = super.transform(pool, toTransform, outWidth, outHeight)
        return roundCrop(pool, transform)
    }

    private fun roundCrop(pool: BitmapPool, source: Bitmap?): Bitmap? {
        if (source == null) {
            return null
        }
        val width = source.width
        val height = source.height
        var result: Bitmap? = pool.get(source.width, source.height, Bitmap.Config.ARGB_8888)

        if (result == null) {
            result = Bitmap.createBitmap(
                source.width, source.height, Bitmap.Config
                    .ARGB_8888
            )
        }
        val canvas = Canvas(result)
        val paint = Paint()
        paint.shader = BitmapShader(
            source, Shader.TileMode.CLAMP, Shader
                .TileMode.CLAMP
        )
        paint.isAntiAlias = true

        val path = Path()
        drawRoundRect(canvas, paint, path, width, height)

        return result
    }

    private fun drawRoundRect(canvas: Canvas, paint: Paint, path: Path, width: Int, height: Int) {
        val rids: FloatArray
        when (mCornerType) {
            CornerType.ALL -> {
                rids = floatArrayOf(
                    mRadius,
                    mRadius,
                    mRadius,
                    mRadius,
                    mRadius,
                    mRadius,
                    mRadius,
                    mRadius
                )
                drawPath(rids, canvas, paint, path, width, height)
            }
            GlideRoundedCornersTransform.CornerType.TOP_LEFT -> {
                rids = floatArrayOf(mRadius, mRadius, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f)
                drawPath(rids, canvas, paint, path, width, height)
            }
            GlideRoundedCornersTransform.CornerType.TOP_RIGHT -> {
                rids = floatArrayOf(0.0f, 0.0f, mRadius, mRadius, 0.0f, 0.0f, 0.0f, 0.0f)
                drawPath(rids, canvas, paint, path, width, height)
            }
            GlideRoundedCornersTransform.CornerType.BOTTOM_RIGHT -> {
                rids = floatArrayOf(0.0f, 0.0f, 0.0f, 0.0f, mRadius, mRadius, 0.0f, 0.0f)
                drawPath(rids, canvas, paint, path, width, height)
            }
            GlideRoundedCornersTransform.CornerType.BOTTOM_LEFT -> {
                rids = floatArrayOf(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, mRadius, mRadius)
                drawPath(rids, canvas, paint, path, width, height)
            }
            GlideRoundedCornersTransform.CornerType.TOP -> {
                rids = floatArrayOf(mRadius, mRadius, mRadius, mRadius, 0.0f, 0.0f, 0.0f, 0.0f)
                drawPath(rids, canvas, paint, path, width, height)
            }
            GlideRoundedCornersTransform.CornerType.BOTTOM -> {
                rids = floatArrayOf(0.0f, 0.0f, 0.0f, 0.0f, mRadius, mRadius, mRadius, mRadius)
                drawPath(rids, canvas, paint, path, width, height)
            }
            GlideRoundedCornersTransform.CornerType.LEFT -> {
                rids = floatArrayOf(mRadius, mRadius, 0.0f, 0.0f, 0.0f, 0.0f, mRadius, mRadius)
                drawPath(rids, canvas, paint, path, width, height)
            }
            GlideRoundedCornersTransform.CornerType.RIGHT -> {
                rids = floatArrayOf(0.0f, 0.0f, mRadius, mRadius, mRadius, mRadius, 0.0f, 0.0f)
                drawPath(rids, canvas, paint, path, width, height)
            }
            GlideRoundedCornersTransform.CornerType.TOP_LEFT_BOTTOM_RIGHT -> {
                rids = floatArrayOf(mRadius, mRadius, 0.0f, 0.0f, mRadius, mRadius, 0.0f, 0.0f)
                drawPath(rids, canvas, paint, path, width, height)
            }
            GlideRoundedCornersTransform.CornerType.TOP_RIGHT_BOTTOM_LEFT -> {
                rids = floatArrayOf(0.0f, 0.0f, mRadius, mRadius, 0.0f, 0.0f, mRadius, mRadius)
                drawPath(rids, canvas, paint, path, width, height)
            }
            GlideRoundedCornersTransform.CornerType.TOP_LEFT_TOP_RIGHT_BOTTOM_RIGHT -> {
                rids =
                    floatArrayOf(mRadius, mRadius, mRadius, mRadius, mRadius, mRadius, 0.0f, 0.0f)
                drawPath(rids, canvas, paint, path, width, height)
            }
            GlideRoundedCornersTransform.CornerType.TOP_RIGHT_BOTTOM_RIGHT_BOTTOM_LEFT -> {
                rids =
                    floatArrayOf(0.0f, 0.0f, mRadius, mRadius, mRadius, mRadius, mRadius, mRadius)
                drawPath(rids, canvas, paint, path, width, height)
            }
            GlideRoundedCornersTransform.CornerType.TOP_LEFT_TOP_RIGHT -> {
                rids = floatArrayOf(mRadius, mRadius, mRadius, mRadius, 0.0f, 0.0f, 0.0f, 0.0f)
                drawPath(rids, canvas, paint, path, width, height)
            }
            else -> throw RuntimeException("RoundedCorners type not belong to CornerType")
        }
    }

    /**@param rids 圆角的半径，依次为左上角xy半径，右上角，右下角，左下角
     */
    private fun drawPath(
        rids: FloatArray,
        canvas: Canvas,
        paint: Paint,
        path: Path,
        width: Int,
        height: Int
    ) {
        path.addRoundRect(RectF(0f, 0f, width.toFloat(), height.toFloat()), rids, Path.Direction.CW)
        canvas.drawPath(path, paint)
    }

    override fun equals(o: Any?): Boolean {
        return o is GlideRoundedCornersTransform
    }

    override fun hashCode(): Int {
        return ID.hashCode()
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(ID_BYTES)
    }

    companion object {
        private const val VERSION = 1
        private const val ID = BuildConfig.APPLICATION_ID + "GlideRoundedCornersTransform." + VERSION
        private val ID_BYTES = ID.toByteArray(Key.CHARSET)
    }
}