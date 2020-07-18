package com.example.kozitskiy_com

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kozitskiy_com.network.RequestManager
import com.example.kozitskiy_com.network.models.DataApiObjects
import com.example.kozitskiy_com.network.models.DataEmailResponse
import com.example.kozitskiy_com.ui.*
import com.example.kozitskiy_com.ui.handlers.EmailFormHandler
import com.example.kozitskiy_com.ui.handlers.MainBodyHandler

class IndexPageActivity : AppCompatActivity(),
    MainReceiver.UiReceiver {

    private lateinit var mainBodyHandler: MainBodyHandler

    val requestManager: RequestManager = RequestManager(this)

    @SuppressLint("CheckResult", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index_page)

        mainBodyHandler = MainBodyHandler(this)

        requestManager.getMainData()
    }

    override fun setMainData(dataApiObjects: DataApiObjects) {
        ViewsSetter(dataApiObjects, this).setAllViews()
    }

    override fun setMainBodyView(action: String) {
        mainBodyHandler.changeBodyByAction(action)
    }

    override fun setEmailBlock(dataEmailResponse: DataEmailResponse) {
        EmailFormHandler(this, dataEmailResponse.response).process()
    }

    override fun onStop() {
        super.onStop()
        requestManager.onActivityDestroy()
    }
}