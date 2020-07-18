package com.example.kozitskiy_com.ui

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.PopupMenu
import android.widget.TextView
import androidx.core.view.children
import com.example.kozitskiy_com.*
import com.example.kozitskiy_com.ui.handlers.EmailFormHandler
import com.example.kozitskiy_com.ui.handlers.FormValidateHandler
import com.example.kozitskiy_com.utils.*
import kotlinx.android.synthetic.main.template_about_me.*
import kotlinx.android.synthetic.main.template_contacts.*
import kotlinx.android.synthetic.main.template_header.*
import kotlinx.android.synthetic.main.template_portfolio.*
import kotlinx.android.synthetic.main.template_skills.*

object ViewListeners {
    fun setStartActionActivityClickListener(
        context: IndexPageActivity, view: View, uri: String, action: String
    ) {
        view.setOnClickListener {
            val trimmedString = uri.trim()
            val intent = Intent(action, Uri.parse(trimmedString))
            context.startActivity(intent)
        }
    }

    fun setHeaderClickListeners(context: IndexPageActivity, popupMenu: PopupMenu) {
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_item_home -> scrollToView(context, context.head_menu)
                R.id.menu_item_about -> scrollToView(context, context.about_me_block)
                R.id.menu_item_skills -> scrollToView(context, context.skills_block)
                R.id.menu_item_portfolio -> scrollToView(context, context.portfolio_block)
                R.id.menu_item_contacts -> scrollToView(context, context.contacts_block)

                else -> false
            }
        }
        context.img_menu_button.setOnClickListener { popupMenu.show() }
        context.head_name.setOnClickListener { scrollToView(context, context.head_menu) }
    }

    fun setPortfolioCategoryClickListener(context: IndexPageActivity) {
        val portfolioCategories = context.portfolio_categories.children
        portfolioCategories.forEach { itCatClick ->
            if (itCatClick.tag == "all") (itCatClick as TextView).highlightTextView("#E91E63")
            val portfolioItems = context.portfolio_items.children
            itCatClick.setOnClickListener { itCat ->
                portfolioCategories.unHighlightPortfolioCategories()
                (itCat as TextView).highlightTextView("#E91E63")
                portfolioItems.hideAllViews()
                portfolioItems.forEach {
                    if (itCat.tag == it.tag) {
                        it.showView()
                    } else if (itCat.tag == "all") {
                        portfolioItems.showAllViews()
                    }
                }
            }
        }
    }

    fun setSendEmailClickListener(context: IndexPageActivity) {
        context.email_btn.setOnClickListener {
            val isFormValid = FormValidateHandler(context).isFormValid()
            if (isFormValid) {
                context.requestManager.getEmailData(
                    context.email_fname.text.toString(),
                    context.email_lname.text.toString(),
                    context.email_email.text.toString(),
                    context.email_msg.text.toString()
                )
            } else {
                EmailFormHandler(context, EMAIL_STATUS_INCORRECT).process()
            }
        }
    }
}