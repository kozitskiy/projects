package com.example.kozitskiy_com.ui.handlers

import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import com.example.kozitskiy_com.*
import com.example.kozitskiy_com.utils.ACTION_INTERNET_CONNECTION
import com.example.kozitskiy_com.utils.ACTION_PROGRESS_BAR_HIDE
import com.example.kozitskiy_com.utils.ACTION_PROGRESS_BAR_SHOW
import kotlinx.android.synthetic.main.activity_index_page.*

class MainBodyHandler(private val indexPageActivity: IndexPageActivity) {

    private val layout = indexPageActivity.main_block as ViewGroup
    private val viewProgress: View =
        indexPageActivity.layoutInflater.inflate(R.layout.item_progress, layout, false)
    private val viewConnection: View? =
        indexPageActivity.layoutInflater.inflate(R.layout.item_connection, layout, false)

    fun changeBodyByAction(action: String) {
        when (action) {
            ACTION_INTERNET_CONNECTION -> showNoInternetConnectionMsg()
            ACTION_PROGRESS_BAR_SHOW -> showProgressBar()
            ACTION_PROGRESS_BAR_HIDE -> hideProgressBar()
        }
    }

    private fun showProgressBar() {
        indexPageActivity.main_block.children.forEach { it.visibility = View.GONE }
        indexPageActivity.main_block.addView(viewProgress)
    }

    private fun hideProgressBar() {
        indexPageActivity.main_block.children.forEach { it.visibility = View.VISIBLE }
        indexPageActivity.main_block.removeView(viewProgress)
    }

    private fun showNoInternetConnectionMsg() {
        indexPageActivity.main_block.children.forEach { it.visibility = View.GONE }
        indexPageActivity.main_block.addView(viewConnection)
    }
}