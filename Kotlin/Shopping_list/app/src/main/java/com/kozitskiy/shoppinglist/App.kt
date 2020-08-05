package com.kozitskiy.shoppinglist

import android.app.Application
import com.kozitskiy.shoppinglist.dagger.*
import com.kozitskiy.shoppinglist.dagger.modules.AdapterModule
import com.kozitskiy.shoppinglist.dagger.modules.BaseModule
import com.kozitskiy.shoppinglist.dagger.modules.DbModule

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent =
            DaggerAppComponent.builder()
                .baseModule(BaseModule())
                .adapterModule(AdapterModule())
                .dbModule(DbModule(this))
                .build()
    }
}