package com.martins.milton.goktest.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

@Suppress("UNCHECKED_CAST")
class ViewBindingUtil {
    companion object {

        fun <T : ViewBinding> inflate(
            inflater: LayoutInflater,
            root: ViewGroup?,
            attachToRoot: Boolean,
            clazz: Class<T>
        ): T {
            return clazz
                .getMethod(
                    "inflate",
                    LayoutInflater::class.java,
                    ViewGroup::class.java,
                    Boolean::class.java
                )
                .invoke(
                    null,
                    inflater,
                    root,
                    attachToRoot
                ) as T
        }
    }
}