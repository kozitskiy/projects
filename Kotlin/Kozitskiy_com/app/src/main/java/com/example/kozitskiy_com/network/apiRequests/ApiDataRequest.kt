package com.example.kozitskiy_com.network.apiRequests

import android.annotation.SuppressLint
import com.example.kozitskiy_com.IndexPageActivity
import com.example.kozitskiy_com.network.ApiService
import com.example.kozitskiy_com.network.models.DataApiObjects
import com.example.kozitskiy_com.utils.ACTION_PROGRESS_BAR_HIDE
import com.example.kozitskiy_com.utils.ACTION_PROGRESS_BAR_SHOW
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ApiDataRequest(context: IndexPageActivity) : ApiRequest(context) {

    @SuppressLint("CheckResult")
    override fun doRequest(): Disposable {
        context.mainBodyChanger(ACTION_PROGRESS_BAR_SHOW)
        return ApiService.getResponseData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::success, this::error)
    }

    override fun <T> success(response: T) {
        context.dataResponseForUi(response as DataApiObjects)
        context.mainBodyChanger(ACTION_PROGRESS_BAR_HIDE)
    }
}