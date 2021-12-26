package com.devandreschavez.moviesgaston.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseContactHolder<T>(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(adapter: T)
}