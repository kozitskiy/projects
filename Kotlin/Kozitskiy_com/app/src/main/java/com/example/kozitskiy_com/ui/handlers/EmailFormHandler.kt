package com.example.kozitskiy_com.ui.handlers

import android.widget.TextView
import androidx.core.view.children
import com.example.kozitskiy_com.*
import com.example.kozitskiy_com.utils.*
import kotlinx.android.synthetic.main.template_contacts.*

class EmailFormHandler(
    private val context: IndexPageActivity,
    private val status: String
) {

    private val emailMsgStatusTextView =   context.email_msg_status

    fun process() {
        emailMsgStatusTextView.showView()
        when (status) {
            EMAIL_STATUS_SUCCESS -> successStatus()
            EMAIL_STATUS_INCORRECT -> incorrectStatus()
            EMAIL_STATUS_FAIL -> failStatus()
        }
    }

    private fun successStatus() {
        context.email_fields.children.forEach { (it as TextView).clear() }
        emailMsgStatusTextView.highlightTextView("#28a745")
        emailMsgStatusTextView.text = EMAIL_MSG_SUCCESS
    }

    private fun incorrectStatus() {
        emailMsgStatusTextView.highlightTextView("#dc3545")
        emailMsgStatusTextView.text = EMAIL_MSG_INCORRECT
    }

    private fun failStatus() {
        emailMsgStatusTextView.highlightTextView("#dc3545")
        emailMsgStatusTextView.text = EMAIL_MSG_FAIL
    }
}