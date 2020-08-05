package com.kozitskiy.shoppinglist.management

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.kozitskiy.shoppinglist.*
import com.kozitskiy.shoppinglist.cache.CacheRepository
import com.kozitskiy.shoppinglist.adapters.ActualPurchasesAdapter
import com.kozitskiy.shoppinglist.adapters.model.DataPurchaseItemView
import com.kozitskiy.shoppinglist.cache.db.model.DataPurchaseItemDb
import com.kozitskiy.shoppinglist.interfaces.ActivityNavigation
import com.kozitskiy.shoppinglist.interfaces.PurchaseReceivers
import com.kozitskiy.shoppinglist.ui.ActualPurchasesFragment
import com.kozitskiy.shoppinglist.utils.Converters
import kotlinx.android.synthetic.main.fragment_actual_purchases_list.*
import kotlinx.android.synthetic.main.recycler_view.*
import javax.inject.Inject

class ManagerActualPurchases @Inject constructor(private val fragment: ActualPurchasesFragment) :
    PurchaseReceivers.ManagerActualPurchasesReceiver {

    @Inject
    lateinit var purchasesAdapter: ActualPurchasesAdapter
    private lateinit var cacheRepository: CacheRepository
    private lateinit var activityIntent: Intent
    private val addedPurchases = ArrayList<DataPurchaseItemView>()

    companion object {
        private var navigation: ActivityNavigation? = null
    }

    override fun onAttach(context: Context) {
        when (context) {
            is ActivityNavigation -> navigation = context
            else -> throw IllegalArgumentException(context.javaClass.name + " not implemented ActivityNavigation")
        }
    }

    override fun onViewCreated() {

        App.appComponent.inject(this)

        fragment.setProgressBody()
        activityIntent = fragment.requireActivity().intent
        cacheRepository = CacheRepository(this)
        cacheRepository.tryGetPurchases(true)

        //setting recyclerView with purchases adapter
        fragment.recycler_view.apply {
            adapter = purchasesAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
    }

    override fun onPause() {
        activityIntent.putExtra(INTENT_COMPLETED_PURCHASE_KEY, addedPurchases)
    }

    override fun onDbResponse(purchaseItems: List<DataPurchaseItemDb>) {

        //Converting purchases db list objects to view list of objects
        val purchasesToView = Converters.convertPurchaseDbListToViewList(purchaseItems)
        val purchasesToViewd = purchasesToView

        purchasesAdapter.setAndRefreshPurchaseList(purchasesToView)
    }

    override fun onSelectAllCheckBoxClicked() {
        when (fragment.select_all_checkbox.isChecked) {
            true -> {
                fragment.swapToFloatingBtnComplete()
                purchasesAdapter.updatePurchasesSelection(true)
            }
            else -> {
                fragment.swapToFloatingBtnAdd()
                purchasesAdapter.updatePurchasesSelection(false)
            }
        }
    }

    //Processing completing purchases.
    //Adding to transferring list and update it in cache with new status
    override fun onCompletePurchaseButtonClicked() {
        val checkedPurchases = purchasesAdapter.checkedPurchaseItemList
        addedPurchases.addAll(checkedPurchases)
        val purchasesIds = Converters.getPurchasesViewUniqueIds(checkedPurchases)
        purchasesAdapter.updatePurchaseList()
        purchasesAdapter.updatePurchasesSelection(false)

        fragment.swapToFloatingBtnAdd()
        fragment.setSelectAllUnchecked()

        cacheRepository.updatePurchaseStatus(purchasesIds)
    }

    override fun onAddPurchaseButtonClicked() {
        navigation?.navigateToAddingPurchasePage()
    }

    override fun onDestroy() {
        cacheRepository.onDestroy()
    }

    //Getting view purchases parcelable list objects from activity intent by key
    //Putting in intent flag for clearing transferred array
    override fun onResume() {
        val intent = fragment.requireActivity().intent
        intent.getParcelableArrayListExtra<DataPurchaseItemView>(INTENT_NEW_PURCHASE_KEY)?.let {
            val dataPurchaseItemView = it as List<DataPurchaseItemView>
            if (dataPurchaseItemView.isNotEmpty()) {
                purchasesAdapter.setAndRefreshPurchaseList(dataPurchaseItemView)
                intent.putExtra(INTENT_CLEAR_ADDED_LIST_KEY, true)
                intent.removeExtra(INTENT_NEW_PURCHASE_KEY)
            }
        }

        //Checking if need to clear transferring purchases list and clear intent by key
        val isClearAdded = activityIntent.getBooleanExtra(INTENT_CLEAR_COMPLETED_LIST_KEY, false)
        if (isClearAdded) {
            addedPurchases.clear()
            activityIntent.removeExtra(INTENT_CLEAR_COMPLETED_LIST_KEY)
        }
        purchasesAdapter.updateFloatingButton()
    }
}