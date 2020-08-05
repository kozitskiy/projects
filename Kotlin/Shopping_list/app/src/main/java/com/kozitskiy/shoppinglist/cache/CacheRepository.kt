package com.kozitskiy.shoppinglist.cache

import com.kozitskiy.shoppinglist.*
import com.kozitskiy.shoppinglist.cache.db.PurchasesDao
import com.kozitskiy.shoppinglist.cache.db.model.DataPurchaseItemDb
import com.kozitskiy.shoppinglist.interfaces.PurchaseReceivers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CacheRepository(
    private val onDbResponseReceiver: PurchaseReceivers.OnDbResponseReceiver?
) :
    PurchaseReceivers.CacheRepositoryReceiver {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var dao: PurchasesDao
    private var disposableActual: Disposable? = null
    private var disposableCompleted: Disposable? = null

    //requests to db using coroutines and rxjava
    override fun savePurchase(purchaseItem: DataPurchaseItemDb) {
        GlobalScope.launch { dao.insertPurchase(purchaseItem) }
    }

    override fun updatePurchaseStatus(purchasesIds: List<Long>) {
        GlobalScope.launch { dao.updatePurchasesStatus(purchasesIds) }
    }

    override fun tryGetPurchases(actual: Boolean) {
        when (actual) {
            true -> {
                disposableActual = dao.selectActualPurchases()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::success, this::error)
            }
            else -> {
                disposableCompleted = dao.selectCompletedPurchases()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::success, this::error)
            }
        }
    }

    //on success response function
    private fun success(dataPurchaseItemsDb: List<DataPurchaseItemDb>) {
        onDbResponseReceiver?.onDbResponse(dataPurchaseItemsDb)
        disposeSubscribes()
    }

    private fun error(t: Throwable) {
        //hardly ever case can come here (local DB only) That's why i didn't processed it
    }

    //unsubscribe all subscribers
    private fun disposeSubscribes() {
        disposableActual?.dispose()
        disposableCompleted?.dispose()
    }

    override fun onDestroy() {
        disposeSubscribes()
    }
}