package com.kozitskiy.shoppinglist.management

import androidx.recyclerview.widget.LinearLayoutManager
import com.kozitskiy.shoppinglist.App
import com.kozitskiy.shoppinglist.INTENT_CLEAR_COMPLETED_LIST_KEY
import com.kozitskiy.shoppinglist.INTENT_COMPLETED_PURCHASE_KEY
import com.kozitskiy.shoppinglist.cache.CacheRepository
import com.kozitskiy.shoppinglist.adapters.CompletedPurchasesAdapter
import com.kozitskiy.shoppinglist.adapters.model.DataPurchaseItemView
import com.kozitskiy.shoppinglist.cache.db.model.DataPurchaseItemDb
import com.kozitskiy.shoppinglist.interfaces.PurchaseReceivers
import com.kozitskiy.shoppinglist.ui.CompletedPurchasesFragment
import com.kozitskiy.shoppinglist.utils.Converters
import kotlinx.android.synthetic.main.recycler_view.*
import javax.inject.Inject

class ManagerCompletedPurchases @Inject constructor(private val fragment: CompletedPurchasesFragment) :
    PurchaseReceivers.ManagerCompletedPurchasesReceiver {

    @Inject
    lateinit var purchasesAdapter: CompletedPurchasesAdapter

    private lateinit var cacheRepository: CacheRepository

    override fun onViewCreated() {

        App.appComponent.inject(this)

        fragment.setProgressBody()
        cacheRepository = CacheRepository(this)

        cacheRepository.tryGetPurchases(false)

        //setting recyclerView with purchases adapter
        fragment.recycler_view.apply {
            adapter = purchasesAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
    }

    override fun onDbResponse(purchaseItems: List<DataPurchaseItemDb>) {

        //Converting purchases db list objects to view list of objects
        val purchasesToView = Converters.convertPurchaseDbListToViewList(purchaseItems)

        purchasesAdapter.setAndRefreshPurchaseList(purchasesToView)
    }

    //Getting view purchases parcelable list objects from activity intent by key
    //Putting in intent flag for clearing transferred array
    override fun onResume() {
        val intent = fragment.requireActivity().intent
        intent.getParcelableArrayListExtra<DataPurchaseItemView>(INTENT_COMPLETED_PURCHASE_KEY)
            ?.let {
                val dataPurchaseItemView = it as List<DataPurchaseItemView>
                if (dataPurchaseItemView.isNotEmpty()) {
                    purchasesAdapter.setAndRefreshPurchaseList(dataPurchaseItemView)
                    intent.putExtra(INTENT_CLEAR_COMPLETED_LIST_KEY, true)
                    intent.removeExtra(INTENT_COMPLETED_PURCHASE_KEY)
                }
            }
    }

    override fun onDestroy() {
        cacheRepository.onDestroy()
    }
}