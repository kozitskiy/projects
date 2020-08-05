package com.kozitskiy.shoppinglist.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kozitskiy.shoppinglist.cache.db.model.DataPurchaseItemDb
import javax.inject.Inject

@Database(
    entities = [DataPurchaseItemDb::class],
    version = 1,
    exportSchema = false
)
abstract class PurchasesDataBase : RoomDatabase() {

    abstract fun getPurchasesDao(): PurchasesDao

//    companion object {
//        fun getInstance(context: Context) = Room.databaseBuilder(
//            context.applicationContext,
//            PurchasesDataBase::class.java,
//            "purchases"
//        ).fallbackToDestructiveMigration()
//            .allowMainThreadQueries()
//            .build()
//    }
}