package com.kozitskiy.shoppinglist.interfaces

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import com.kozitskiy.shoppinglist.cache.db.model.DataPurchaseItemDb
import com.kozitskiy.shoppinglist.adapters.viewHolders.ActualPurchasesViewHolder
import dagger.Module
import dagger.Provides
import javax.inject.Inject

interface PurchaseReceivers {

    interface OnAccessPermissions {
        fun onRequestPermissionsResult(requestCode: Int, grantResults: IntArray)
    }

    interface OnSetBodyView {
        fun setEmptyBody()
        fun setContentBody()
        fun setProgressBody()
    }

    interface OnDestroySubscription {
        fun onDestroy()
    }

    interface OnResumeSubscription {
        fun onResume()
    }

    interface OnPauseSubscription {
        fun onPause()
    }

    interface OnViewCreatedSubscription {
        fun onViewCreated()
    }

    interface OnDbResponseReceiver {
        fun onDbResponse(purchaseItems: List<DataPurchaseItemDb>)
    }

    interface ViewActualPurchaseReceiver :
        OnSetBodyView {
        fun swapToFloatingBtnAdd()
        fun swapToFloatingBtnComplete()
        fun setSelectAllUnchecked()
    }

    interface ManagerCompletedPurchasesReceiver : OnDestroySubscription,
        OnDbResponseReceiver,
        OnResumeSubscription,
        OnViewCreatedSubscription

    interface ManagerActualPurchasesReceiver : OnDestroySubscription, OnDbResponseReceiver,
        OnResumeSubscription, OnViewCreatedSubscription,
        OnPauseSubscription {
        fun onAttach(context: Context)
        fun onSelectAllCheckBoxClicked()
        fun onCompletePurchaseButtonClicked()
        fun onAddPurchaseButtonClicked()
    }

    interface ManagerAddingPurchaseReceiver : OnViewCreatedSubscription,
        OnResumeSubscription,
        OnPauseSubscription {
        fun addPurchaseClicked()
        fun uploadImageClicked()
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
        fun onRequestPermissionsResult(requestCode: Int, grantResults: IntArray)
    }


    interface ViewAddingPurchaseReceiver {
        fun setAddedImageView(bitmap: Bitmap)
        fun showPurchaseSuccessMsg()
        fun showPurchaseErrorMsg()
        fun clearPurchaseAddingFields()
    }

    interface CacheRepositoryReceiver {
        fun savePurchase(purchaseItem: DataPurchaseItemDb)
        fun updatePurchaseStatus(purchasesIds: List<Long>)
        fun tryGetPurchases(actual: Boolean)
        fun onDestroy()
    }

    interface RecyclerViewRow {
        fun bind()
        fun setPurchaseBitmapImg(bitmap: Bitmap)
        fun setPurchaseDefaultImg()
        fun setPurchaseName(name: String)
        fun setPurchaseNumber(number: Int)
    }

    interface OnPurchaseCheckBoxClickListener {
        fun onItemCheckBoxClick(viewHolder: ActualPurchasesViewHolder, position: Int)
    }
}