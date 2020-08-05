package com.kozitskiy.shoppinglist.adapters.viewHolders

import android.graphics.Bitmap
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kozitskiy.shoppinglist.DEFAULT_PURCHASE_IMAGE
import com.kozitskiy.shoppinglist.interfaces.PurchaseReceivers
import kotlinx.android.synthetic.main.item_actual_purchase.view.*

class CompletedPurchasesViewHolder(view: View) :
    RecyclerView.ViewHolder(view), PurchaseReceivers.RecyclerViewRow {

    override fun bind() {
        //nothing to do
    }

    //Setting recyclerview views
    override fun setPurchaseDefaultImg() {
        itemView.purchase_saved_img.setImageResource(DEFAULT_PURCHASE_IMAGE)
    }

    override fun setPurchaseBitmapImg(bitmap: Bitmap) {
        itemView.purchase_saved_img.setImageBitmap(bitmap)
    }

    override fun setPurchaseName(name: String) {
        itemView.purchase_saved_name.text = name
    }

    override fun setPurchaseNumber(number: Int) {
        itemView.purchase_saved_num.text = (number + 1).toString()
    }
}