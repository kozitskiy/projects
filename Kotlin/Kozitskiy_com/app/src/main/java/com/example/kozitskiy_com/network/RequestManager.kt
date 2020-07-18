package com.example.kozitskiy_com.network

import com.example.kozitskiy_com.IndexPageActivity
import com.example.kozitskiy_com.MainReceiver
import com.example.kozitskiy_com.network.apiRequests.ApiDataRequest
import com.example.kozitskiy_com.network.apiRequests.ApiEmailRequest
import io.reactivex.disposables.Disposable

class RequestManager(
    private val context: IndexPageActivity
) : MainReceiver.ManagerReceiver {
    private var emailDisposable: Disposable? = null
    private var dataDisposable: Disposable? = null

    override fun getMainData() {
        dataDisposable = ApiDataRequest(context).doRequest()
    }

    override fun getEmailData(fName: String, lName: String, email: String, msg: String) {
        emailDisposable = ApiEmailRequest(context, fName, lName, email, msg).doRequest()
    }

    override fun onActivityDestroy() {
        emailDisposable?.dispose()
        dataDisposable?.dispose()
    }
}