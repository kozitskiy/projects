package com.example.kozitskiy_com.ui.handlers

import com.example.kozitskiy_com.IndexPageActivity
import kotlinx.android.synthetic.main.template_contacts.*

class FormValidateHandler(private val context: IndexPageActivity) {

    fun isFormValid() = isTrivialFieldsValid() && isEmailValid()

    private fun isTrivialFieldsValid() = when {
        context.email_fname.text.length < 2 -> false
        context.email_lname.text.length < 2 -> false
        context.email_msg.text.length < 5 -> false
        else -> true
    }

    private fun isEmailValid(): Boolean {
        val regex =
            Regex("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$")
        return regex.containsMatchIn(context.email_email.text.toString())
    }
}