package com.example.firstcodedemo.ui

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.firstcodedemo.R
import com.example.firstcodedemo.activity.FruitActivity
import com.example.firstcodedemo.bean.Fruit
import com.example.firstcodedemo.utils.GlideRoundedCornersTransform

class FruitAdapter(private val context: Context, private val fruits: MutableList<Fruit>) :
    RecyclerView.Adapter<FruitAdapter.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fruit_item, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = fruits.size


    override fun onBindViewHolder(viewHolder: ViewHolder, p1: Int) {
        val fruit = fruits[p1]
        viewHolder.fruitName.text = fruit.name

        viewHolder.fruitImage.setOnClickListener {
            if (p1 == fruits.size - 1) {
                addListener?.addFruit()
            } else {
                val intent = Intent(context, FruitActivity::class.java)
                intent.putExtra("fruitName", fruit.name)
                intent.putExtra("fruitId", fruit.imgId)
                context.startActivity(intent)
            }
        }

        val roundedCorners = RoundedCorners(6)
        val override = RequestOptions.bitmapTransform(roundedCorners).override(300, 300)


        val options =
            RequestOptions.circleCropTransform()

//        Glide.with(context).load(fruit.imgId)
//            .apply(RequestOptions.bitmapTransform(RoundedCorners(20))).into(viewHolder.fruitImage)
//        Glide.with(context).load(fruit.imgId)
//            .apply(RequestOptions.bitmapTransform( CircleCrop())).into(viewHolder.fruitImage)


        val myOptions = RequestOptions().optionalTransform(
            GlideRoundedCornersTransform(
                5f
                , GlideRoundedCornersTransform.CornerType.ALL
            )
        )
        Glide.with(context).load(fruit.imgId)
            .apply(myOptions).into(viewHolder.fruitImage)

    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val cardView: CardView = view as CardView
        val fruitImage: ImageView = cardView.findViewById(R.id.fruit_img)
        val fruitName: TextView = cardView.findViewById(R.id.fruit_name)
    }



    //监听的回调
    private var addListener: AddListener? = null

    interface AddListener {
        fun addFruit()
    }

    fun setAddListener(listener: AddListener) {
        this.addListener = listener
    }

}