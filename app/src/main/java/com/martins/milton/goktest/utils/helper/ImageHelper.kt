package com.martins.milton.goktest.utils.helper

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.martins.milton.goktest.R
import com.martins.milton.goktest.utils.ext.getDrawableCompat

class ImageHelper(private val context: Context) {

    enum class CropType {
        NONE, CIRCULAR
    }

    fun loadImage(
        url: String,
        view: ImageView,
        cropType: CropType = CropType.NONE
    ) {
        val options = RequestOptions()

        when (cropType) {
            CropType.CIRCULAR -> {
                options.circleCrop()
                options.error(context.getDrawableCompat(R.drawable.ic_account))
            }
            else -> {
                options.error(context.getDrawableCompat(R.drawable.no_image))
            }
        }

        Glide
            .with(context)
            .load(url)
            .apply(options)
            .placeholder(getProgressDrawable())
            .signature(ObjectKey(url))
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(view)
    }

    private fun getProgressDrawable(): CircularProgressDrawable {
        val circularProgressDrawable = CircularProgressDrawable(context).apply {
            strokeWidth = context.resources.getDimension(R.dimen.progress_indicator_stroke_width)
            centerRadius = context.resources.getDimension(R.dimen.progress_indicator_radius)
        }

        circularProgressDrawable.start()

        return circularProgressDrawable
    }
}