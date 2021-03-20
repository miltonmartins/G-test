package com.martins.milton.goktest.utils.ext

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.martins.milton.goktest.R

fun Context.showMessage(message: String) {
    Toast.makeText(
        this,
        message,
        Toast.LENGTH_LONG
    ).show()
}

@SuppressLint("ResourceAsColor")
fun Context.showSnack(
    view: View,
    message: String?,
    @ColorRes backgroundColor: Int
) {
    Snackbar.make(
        view,
        message ?: getString(R.string.error_default),
        Snackbar.LENGTH_SHORT
    ).setBackgroundTint(backgroundColor)
        .show()
}

fun Context.getColorCompat(@ColorRes color: Int): Int {
    return ContextCompat.getColor(
        this,
        color
    )
}

fun Context.getDrawableCompat(@DrawableRes drawableRes: Int): Drawable? {
    return ContextCompat.getDrawable(
        this,
        drawableRes
    )
}

fun Context.setSecondTextColor(text: String): SpannableStringBuilder {
    val stringBuilder = SpannableStringBuilder(text)
    val colorSpan = ForegroundColorSpan(this.getColorCompat(R.color.gray))
    val indexGap = text.indexOfFirst { it == ' ' }

    if (indexGap != -1) {
        stringBuilder.setSpan(
            colorSpan,
            indexGap,
            text.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
    }

    return stringBuilder
}

fun View.setVisible(visible: Boolean) {
    this.visibility = when (visible) {
        true -> View.VISIBLE
        false -> View.GONE
    }
}

fun RecyclerView.setup(
    adapter: RecyclerView.Adapter<*>,
    layoutManager: RecyclerView.LayoutManager
) {
    this.adapter = adapter
    this.layoutManager = layoutManager
    this.setHasFixedSize(true)
}


