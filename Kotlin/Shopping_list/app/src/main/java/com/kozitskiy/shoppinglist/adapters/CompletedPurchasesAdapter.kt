package com.kozitskiy.shoppinglist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kozitskiy.shoppinglist.*
import com.kozitskiy.shoppinglist.adapters.model.DataPurchaseItemView
import com.kozitskiy.shoppinglist.adapters.viewHolders.CompletedPurchasesViewHolder
import com.kozitskiy.shoppinglist.ui.CompletedPurchasesFragment
import javax.inject.Inject

class CompletedPurchasesAdapter @Inject constructor(private val fragment: CompletedPurchasesFragment) :
    RecyclerView.Adapter<CompletedPurchasesViewHolder>() {

    private val purchaseItemList = ArrayList<DataPurchaseItemView>()

    //Purchases list updater and call list status setter depends on list status
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CompletedPurchasesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_completed_purchase, parent, false)
        )

    override fun onBindViewHolder(holder: CompletedPurchasesViewHolder, position: Int) {

        //holder.bind() //not used yet

        //Passing data to set views
        val purchaseItem = purchaseItemList[position]
        purchaseItem.bitmapImg?.let {
            holder.setPurchaseBitmapImg(it)
        } ?: holder.setPurchaseDefaultImg()

        holder.setPurchaseNumber(position)
        holder.setPurchaseName(purchaseItem.name)
    }

    override fun getItemCount() = purchaseItemList.size
}