package com.kozitskiy.shoppinglist.utils

import android.annotation.SuppressLint
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import com.kozitskiy.shoppinglist.R

fun EditText.getStringValue() = this.text.toString()

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun View.hideView() {
    this.visibility = View.INVISIBLE
}

fun CheckBox.checked() {
    this.isChecked = true
}

fun CheckBox.unChecked() {
    this.isChecked = false
}

fun View.showForShortTime() {
    this.showView()
    this.postDelayed({ this.visibility = View.INVISIBLE }, 1000)
}

@SuppressLint("ResourceAsColor")
fun TextView.setSuccessColor(){
    this.setTextColor(R.color.successMsg);
}