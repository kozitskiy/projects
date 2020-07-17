package com.example.kozitskiy_com.network

import android.annotation.SuppressLint
import android.widget.Toast
import com.example.kozitskiy_com.IndexPageActivity
import com.example.kozitskiy_com.network.models.DataApiObjects
import com.example.kozitskiy_com.network.models.DataEmailRequest
import com.example.kozitskiy_com.network.models.DataEmailResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlin.math.ln

private const val KEY_FOR_SEND_EMAIL = "enabled"

class ApiEmailRequestOld(
    private val indexPageActivity: IndexPageActivity,
    private val fName: String,
    private val lName: String,
    private val email: String,
    private val msg: String
) {



    @SuppressLint("CheckResult")
    fun doRequestToSendEmail() {

        val dataEmailRequest = DataEmailRequest(KEY_FOR_SEND_EMAIL, fName, lName, email, msg)

        ApiService.getResponseEmail(dataEmailRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::success, this::error)
//      val networkData: Disposable = ApiService.getResponseData()
    }

    private fun success(responseEmail: DataEmailResponse) {
        indexPageActivity.emailResponseForUi(responseEmail)
    }

    private fun error(t: Throwable) {
        Toast.makeText(indexPageActivity, "Sending Error", Toast.LENGTH_SHORT).show()
        t.printStackTrace()
    }


}

