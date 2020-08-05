package com.kozitskiy.shoppinglist.adapters.viewHolders

import android.graphics.Bitmap
import android.util.SparseBooleanArray
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kozitskiy.shoppinglist.*
import com.kozitskiy.shoppinglist.adapters.ActualPurchasesAdapter
import com.kozitskiy.shoppinglist.interfaces.PurchaseReceivers
import com.kozitskiy.shoppinglist.utils.checked
import com.kozitskiy.shoppinglist.utils.unChecked
import kotlinx.android.synthetic.main.item_actual_purchase.view.*

class ActualPurchasesViewHolder(
    view: View,
    private val adapter: ActualPurchasesAdapter,
    private val itemStates: SparseBooleanArray
) :
    RecyclerView.ViewHolder(view), PurchaseReceivers.RecyclerViewRow {

    //Checkbox click listener to set check/uncheck and save state
    override fun bind() {
        itemView.purchase_checkbox.setOnClickListener {
            adapter.onItemCheckBoxClick(this, layoutPosition)
        }
        when (itemStates.get(layoutPosition, false)) {
            true -> itemView.purchase_checkbox.checked()
            false -> itemView.purchase_checkbox.unChecked()
        }
    }

    //Set recyclerview views
    override fun setPurchaseBitmapImg(bitmap: Bitmap) {
        itemView.purchase_saved_img.setImageBitmap(bitmap)
    }

    override fun setPurchaseDefaultImg() {
        itemView.purchase_saved_img.setImageResource(DEFAULT_PURCHASE_IMAGE)
    }

    override fun setPurchaseName(name: String) {
        itemView.purchase_saved_name.text = name
    }

    override fun setPurchaseNumber(number: Int) {
        itemView.purchase_saved_num.text = (number + 1).toString()
    }
}