package com.example.firstcodedemo.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.Nullable
import android.util.AttributeSet
import android.widget.TextView

class MTextView : TextView{

    constructor(context: Context):super(context)

    constructor(context: Context, @Nullable attrs: AttributeSet?) : super(context,attrs)

    constructor(context: Context, @Nullable attrs: AttributeSet?, defStyleAttr: Int):super(context,attrs,defStyleAttr)


}