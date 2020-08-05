package com.kozitskiy.shoppinglist.cache.db

import androidx.room.*
import com.kozitskiy.shoppinglist.cache.db.model.DataPurchaseItemDb
import io.reactivex.Flowable

@Dao
interface PurchasesDao {

    //queries depend on our condition
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPurchase(purchases: DataPurchaseItemDb)

    @Query("UPDATE DataPurchaseItemDb SET status = 1 WHERE uniqueId IN (:purchasesUniqueIds)")
    fun updatePurchasesStatus(purchasesUniqueIds: List<Long>)

    @Query("SELECT * FROM DataPurchaseItemDb WHERE status = 0")
    fun selectActualPurchases(): Flowable<List<DataPurchaseItemDb>>

    @Query("SELECT * FROM DataPurchaseItemDb WHERE status = 1")
    fun selectCompletedPurchases(): Flowable<List<DataPurchaseItemDb>>
}