package com.kozitskiy.shoppinglist.dagger.modules

import android.content.Context
import androidx.room.Room
import com.kozitskiy.shoppinglist.cache.db.PurchasesDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideDataBaseDao() = Room.databaseBuilder(
        context.applicationContext,
        PurchasesDataBase::class.java,
        "purchases"
    ).fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build().getPurchasesDao()
}