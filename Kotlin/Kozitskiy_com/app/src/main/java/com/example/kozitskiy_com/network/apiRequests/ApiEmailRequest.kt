package com.example.kozitskiy_com.network.apiRequests

import android.annotation.SuppressLint
import com.example.kozitskiy_com.IndexPageActivity
import com.example.kozitskiy_com.network.ApiService
import com.example.kozitskiy_com.network.models.DataEmailRequest
import com.example.kozitskiy_com.network.models.DataEmailResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

private const val KEY_FOR_SEND_EMAIL = "enabled"

class ApiEmailRequest(
    context: IndexPageActivity,
    private val fName: String,
    private val lName: String,
    private val email: String,
    private val msg: String
) : ApiRequest(context) {


    @SuppressLint("CheckResult")
    override fun doRequest(): Disposable {
        val dataEmailRequest = DataEmailRequest(KEY_FOR_SEND_EMAIL, fName, lName, email, msg)
        return ApiService.getResponseEmail(dataEmailRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::success, this::error)
    }

    override fun <T> success(response: T) {
        context.emailResponseForUi(response as DataEmailResponse)
    }
}