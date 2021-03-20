package com.martins.milton.goktest.ui.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.martins.milton.goktest.utils.ViewBindingUtil

class SimpleRecyclerViewAdapter<T, K : ViewBinding>(
    private val clazz: Class<K>,
    private val onBind: View.(Int, T, K) -> Unit
) : RecyclerView.Adapter<SimpleRecyclerViewAdapter.ItemViewHolder<T>>() {

    var items = listOf<T>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder<T> {
        val binding = ViewBindingUtil.inflate(
            inflater = LayoutInflater.from(parent.context),
            root = parent,
            attachToRoot = false,
            clazz = clazz
        )

        return ItemViewHolder(binding)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(
        holder: ItemViewHolder<T>,
        position: Int
    ) {
        (holder as? ItemViewHolder<T>)?.bind(
            item = items[position],
            onBind = onBind as View.(Int, T, ViewBinding) -> Unit
        )
    }

    class ItemViewHolder<T>(
        private val binding: ViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: T,
            onBind: View.(Int, T, K: ViewBinding) -> Unit
        ) {
            itemView.onBind(
                adapterPosition,
                item,
                binding
            )
        }
    }
}