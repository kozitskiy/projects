package com.example.kozitskiy_com

import com.example.kozitskiy_com.network.models.DataApiObjects
import com.example.kozitskiy_com.network.models.DataEmailResponse

interface MainReceiver {

    interface UiReceiver {
        fun setMainData(dataApiObjects: DataApiObjects)

        fun setMainBodyView(action: String)

        fun setEmailBlock(dataEmailResponse: DataEmailResponse)
    }

    interface ManagerReceiver {
        fun getMainData()

        fun getEmailData(fName: String, lName: String, email: String, msg: String)

        fun onActivityDestroy()
    }
}