package com.example.kozitskiy_com.ui

import com.example.kozitskiy_com.network.models.DataApiObjects
import com.example.kozitskiy_com.network.models.DataEmailResponse
import io.reactivex.disposables.Disposable

interface UiDataReceiver {

    fun dataResponseForUi(dataApiObjects: DataApiObjects)

    fun mainBodyChanger(action: String)

    fun emailResponseForUi(dataEmailResponse: DataEmailResponse)

    fun disposableRx(disposable: Disposable)
}