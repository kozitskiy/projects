package com.kozitskiy.shoppinglist.management

import android.content.Intent
import android.graphics.Bitmap
import com.kozitskiy.shoppinglist.App
import com.kozitskiy.shoppinglist.INTENT_CLEAR_ADDED_LIST_KEY
import com.kozitskiy.shoppinglist.INTENT_NEW_PURCHASE_KEY
import com.kozitskiy.shoppinglist.adapters.model.DataPurchaseItemView
import com.kozitskiy.shoppinglist.cache.CacheRepository
import com.kozitskiy.shoppinglist.cache.db.model.DataPurchaseItemDb
import com.kozitskiy.shoppinglist.interfaces.PurchaseReceivers
import com.kozitskiy.shoppinglist.management.handlers.ImageLoadHandler
import com.kozitskiy.shoppinglist.ui.AddingPurchasesFragment
import com.kozitskiy.shoppinglist.utils.Converters
import com.kozitskiy.shoppinglist.utils.getStringValue
import kotlinx.android.synthetic.main.fragment_add_purchases.*
import javax.inject.Inject

class ManagerAddingPurchases @Inject constructor(private val fragment: AddingPurchasesFragment) :
    PurchaseReceivers.ManagerAddingPurchaseReceiver {

    private lateinit var cacheRepository: CacheRepository
    @Inject
    lateinit var imageLoadHandler: ImageLoadHandler
    private var loadedBitmap: Bitmap? = null
    private val addedPurchases = ArrayList<DataPurchaseItemView>()
    private lateinit var activityIntent: Intent

    override fun onViewCreated() {

        App.appComponent.inject(this)

        activityIntent = fragment.requireActivity().intent
        cacheRepository = CacheRepository(null)
    }

    //Checking if need to clear transferring purchases list and clear intent by key
    override fun onResume() {
        val isClearAdded = activityIntent.getBooleanExtra(INTENT_CLEAR_ADDED_LIST_KEY, false)
        if (isClearAdded) {
            addedPurchases.clear()
            activityIntent.removeExtra(INTENT_CLEAR_ADDED_LIST_KEY)
        }
    }

    override fun uploadImageClicked() {
        imageLoadHandler.activateImageUploader()
    }

    override fun onPause() {
        activityIntent.putExtra(INTENT_NEW_PURCHASE_KEY, addedPurchases)
    }

    //Processing adding purchases.
    //Adding to transferring list and cache
    override fun addPurchaseClicked() {
        val purchaseName = fragment.purchase_adding_name.getStringValue()
        val uniqueId = System.currentTimeMillis()
        when (purchaseName.isNotEmpty()) {
            true -> {
                var decodedImg: String? = null
                loadedBitmap?.let {
                    decodedImg = Converters.convertToBase64(it)
                }
                addedPurchases.add(DataPurchaseItemView(uniqueId, loadedBitmap, purchaseName))
                cacheRepository.savePurchase(DataPurchaseItemDb(0, uniqueId, decodedImg, purchaseName))
                fragment.showPurchaseSuccessMsg()
                fragment.clearPurchaseAddingFields()
                loadedBitmap = null
            }
            false -> fragment.showPurchaseErrorMsg()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadedBitmap = imageLoadHandler.getLoadedBitmap(requestCode, resultCode, data)
        loadedBitmap?.let {
            fragment.setAddedImageView(it)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, grantResults: IntArray
    ) {
        imageLoadHandler.onRequestPermissionsResult(requestCode, grantResults)
    }
}