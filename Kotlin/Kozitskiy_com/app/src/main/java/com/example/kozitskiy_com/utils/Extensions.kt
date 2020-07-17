package com.example.kozitskiy_com.utils

import android.graphics.Color
import android.view.View
import android.widget.TextView

fun String.replaceBrToNewLine() = this.replace("<br>", "\n")

fun Sequence<View>.hideAllViews() = this.forEach { it.visibility = View.GONE }

fun Sequence<View>.showAllViews() = this.forEach { it.visibility = View.VISIBLE }

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun TextView.clear() {
    this.text = ""
}

fun TextView.highlightTextView(color: String) = setTextColor(Color.parseColor(color))

fun Sequence<View>.unHighlightPortfolioCategories() =
    this.forEach { (it as TextView).setTextColor(Color.BLACK) }

