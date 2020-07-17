package com.example.kozitskiy_com.network

import com.example.kozitskiy_com.IndexPageActivity
import com.example.kozitskiy_com.network.apiRequests.ApiDataRequest
import com.example.kozitskiy_com.network.apiRequests.ApiEmailRequest
import io.reactivex.disposables.Disposable

class NetworkManager(
    private val context: IndexPageActivity
) {
    private lateinit var disposable: Disposable

    fun sendEmail(fName: String, lName: String, email: String, msg: String) {
        disposable = ApiEmailRequest(context, fName, lName, email, msg).doRequest()
        context.disposableRx(disposable)
    }

    fun getDataForUi() {
        disposable = ApiDataRequest(context).doRequest()
        context.disposableRx(disposable)
    }
}