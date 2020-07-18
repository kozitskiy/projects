package com.example.kozitskiy_com.network.apiRequests

import com.example.kozitskiy_com.IndexPageActivity
import com.example.kozitskiy_com.utils.ACTION_INTERNET_CONNECTION
import com.example.kozitskiy_com.utils.ACTION_PROGRESS_BAR_HIDE
import io.reactivex.disposables.Disposable

abstract class ApiRequest(protected val context: IndexPageActivity) {

    abstract fun doRequest(): Disposable

    protected abstract fun <T> success(response: T)

    protected fun error(t: Throwable) {
        context.setMainBodyView(ACTION_PROGRESS_BAR_HIDE)
        context.setMainBodyView(ACTION_INTERNET_CONNECTION)
        t.printStackTrace()
    }
}