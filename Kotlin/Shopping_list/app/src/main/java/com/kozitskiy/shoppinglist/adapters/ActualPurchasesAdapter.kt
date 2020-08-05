package com.kozitskiy.shoppinglist.adapters

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kozitskiy.shoppinglist.*
import com.kozitskiy.shoppinglist.adapters.model.DataPurchaseItemView
import com.kozitskiy.shoppinglist.adapters.viewHolders.ActualPurchasesViewHolder
import com.kozitskiy.shoppinglist.interfaces.PurchaseReceivers
import com.kozitskiy.shoppinglist.ui.ActualPurchasesFragment
import kotlinx.android.synthetic.main.item_actual_purchase.view.*

class ActualPurchasesAdapter(
    private val fragment: ActualPurchasesFragment
) :
    RecyclerView.Adapter<ActualPurchasesViewHolder>(),
    PurchaseReceivers.OnPurchaseCheckBoxClickListener {

    private val purchaseItemList = ArrayList<DataPurchaseItemView>()
    val checkedPurchaseItemList = ArrayList<DataPurchaseItemView>()

    //SparseBooleanArray for saving checkbox states
    private var itemStates = SparseBooleanArray()

    fun updateFloatingButton() {
        when (checkedPurchaseItemList.isEmpty()) {
            true -> fragment.swapToFloatingBtnAdd()
            else -> fragment.swapToFloatingBtnComplete()
        }
    }

    //Different updaters for purchase list depend on the condition
    fun updatePurchaseList() {
        itemStates.clear()
        purchaseItemList.removeAll(checkedPurchaseItemList)
        checkedPurchaseItemList.clear()

        when (purchaseItemList.isEmpty()) {
            true -> fragment.setEmptyBody()
        }
        notifyDataSetChanged()
    }

    fun setAndRefreshPurchaseList(purchases: List<DataPurchaseItemView>) {
        purchaseItemList.apply {
//            clear()
            addAll(purchases)
        }
        when (purchaseItemList.isEmpty()) {
            true -> fragment.setEmptyBody()
            else -> fragment.setContentBody()
        }
        notifyDataSetChanged()
    }

    fun updatePurchasesSelection(isSelectedAll: Boolean) {

        if (isSelectedAll) {
            purchaseItemList.forEachIndexed { index, _ -> itemStates.put(index, true) }
            checkedPurchaseItemList.addAll(purchaseItemList)
        } else {
            purchaseItemList.forEachIndexed { index, _ -> itemStates.put(index, false) }
            checkedPurchaseItemList.removeAll(purchaseItemList)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ActualPurchasesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_actual_purchase, parent, false),
            this, itemStates
        )

    override fun onBindViewHolder(holder: ActualPurchasesViewHolder, position: Int) {

        //Passing data to set views
        holder.bind()
        val purchaseItem = purchaseItemList[position]
        purchaseItem.bitmapImg?.let {
            holder.setPurchaseBitmapImg(it)
        } ?: holder.setPurchaseDefaultImg()

        holder.setPurchaseNumber(position)
        holder.setPurchaseName(purchaseItem.name)
    }

    override fun getItemCount() = purchaseItemList.size


    //Fill list with checked items
    //Change button depending on status of List
    override fun onItemCheckBoxClick(viewHolder: ActualPurchasesViewHolder, position: Int) {

        when (itemStates[position, false]) {
            true -> itemStates.put(position, false)
            false -> itemStates.put(position, true)
        }

        when (viewHolder.itemView.purchase_checkbox.isChecked) {
            true -> checkedPurchaseItemList.add(purchaseItemList[position])
            false -> {
                fragment.setSelectAllUnchecked()
                checkedPurchaseItemList.remove(purchaseItemList[position])
            }
        }

        when (checkedPurchaseItemList.isEmpty()) {
            true -> fragment.swapToFloatingBtnAdd()
            else -> fragment.swapToFloatingBtnComplete()
        }
    }
}