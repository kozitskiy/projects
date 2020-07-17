package com.example.kozitskiy_com

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kozitskiy_com.network.NetworkManager
import com.example.kozitskiy_com.network.models.DataApiObjects
import com.example.kozitskiy_com.network.models.DataEmailResponse
import com.example.kozitskiy_com.ui.*
import com.example.kozitskiy_com.ui.handlers.EmailFormHandler
import com.example.kozitskiy_com.ui.handlers.MainBodyHandler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class IndexPageActivity : AppCompatActivity(), UiDataReceiver {

    private lateinit var mainBodyHandler: MainBodyHandler
    private var disposables: CompositeDisposable = CompositeDisposable()

    @SuppressLint("CheckResult", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index_page)

        mainBodyHandler = MainBodyHandler(this)

        NetworkManager(this).getDataForUi()
    }

    override fun dataResponseForUi(dataApiObjects: DataApiObjects) {
        ViewsSetter(dataApiObjects, this).setAllViews()
    }

    override fun mainBodyChanger(action: String) {
        mainBodyHandler.changeBodyByAction(action)
    }

    override fun emailResponseForUi(dataEmailResponse: DataEmailResponse) {
        EmailFormHandler(this, dataEmailResponse.response).process()
    }

    override fun disposableRx(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onStop() {
        super.onStop()
        disposables.dispose()
    }
}