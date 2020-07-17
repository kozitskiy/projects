package com.example.kozitskiy_com.utils

import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.kozitskiy_com.IndexPageActivity
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.android.synthetic.main.activity_index_page.*

const val SVG_FORMAT_IMG_TO_LOAD = "svg"
const val PNG_FORMAT_IMG_TO_LOAD = "png"
const val JPG_FORMAT_IMG_TO_LOAD = "jpg"

const val EMAIL_MSG_SUCCESS = "Message has been sent!"
const val EMAIL_MSG_FAIL = "Please, try again later"
const val EMAIL_MSG_INCORRECT = "Please, fill out the form correctly"
const val EMAIL_STATUS_SUCCESS = "success"
const val EMAIL_STATUS_INCORRECT = "incorrect"
const val EMAIL_STATUS_FAIL = "fail"

const val ACTION_INTERNET_CONNECTION = "internetConnection"
const val ACTION_PROGRESS_BAR_HIDE = "progressBarHide"
const val ACTION_PROGRESS_BAR_SHOW = "progressBarShow"

fun setImageWithGlide(imageView: ImageView, imgUrl: String, format: String) {
    when (format) {
        JPG_FORMAT_IMG_TO_LOAD, PNG_FORMAT_IMG_TO_LOAD -> Glide.with(imageView.context).load(imgUrl)
            .into(imageView)
        SVG_FORMAT_IMG_TO_LOAD -> GlideToVectorYou.init()
            .with(imageView.context).load(Uri.parse(imgUrl), imageView)
    }
}

fun scrollToView(indexPageActivity: IndexPageActivity, view: View): Boolean {
    indexPageActivity.scroll_view_main.scrollTo(0, view.top)
    return true
}

fun getItemView(context: IndexPageActivity, layout: ViewGroup, resource: Int) =
    context.layoutInflater.inflate(resource, layout, false) as View