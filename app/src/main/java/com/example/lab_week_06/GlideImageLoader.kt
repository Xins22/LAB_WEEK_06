package com.example.lab_week_06

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader (private val context: Context) : ImageLoader {
    override fun loadImage(imageUrl: String?, imageView: ImageView?) {
        if (imageView != null) {
            Glide.with(context)
                .load(imageUrl)
                .centerCrop()
                .into(imageView)
        }
    }
}
