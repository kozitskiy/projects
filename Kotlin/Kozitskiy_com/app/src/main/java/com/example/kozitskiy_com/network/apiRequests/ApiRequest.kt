package com.example.kozitskiy_com.network.apiRequests

import android.widget.Toast
import com.example.kozitskiy_com.IndexPageActivity
import io.reactivex.disposables.Disposable

abstract class ApiRequest(protected val context: IndexPageActivity) {

    abstract fun doRequest(): Disposable

    protected abstract fun <T> success(response: T)

    protected fun error(t: Throwable) {
        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        t.printStackTrace()
    }
}