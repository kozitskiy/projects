package com.kozitskiy.shoppinglist.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kozitskiy.shoppinglist.*
import com.kozitskiy.shoppinglist.interfaces.PurchaseReceivers
import com.kozitskiy.shoppinglist.management.ManagerActualPurchases
import com.kozitskiy.shoppinglist.utils.hideView
import com.kozitskiy.shoppinglist.utils.showView
import com.kozitskiy.shoppinglist.utils.unChecked
import kotlinx.android.synthetic.main.fragment_actual_purchases_list.*
import kotlinx.android.synthetic.main.item_empty_list.*
import kotlinx.android.synthetic.main.item_progress.*
import javax.inject.Inject

class ActualPurchasesFragment @Inject constructor() : Fragment(),
    PurchaseReceivers.ViewActualPurchaseReceiver {

    @Inject
    lateinit var managerActualPurchases: ManagerActualPurchases

    override fun onAttach(context: Context) {
        super.onAttach(context)

        App.appComponent.inject(this)

        managerActualPurchases.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_actual_purchases_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        managerActualPurchases.onViewCreated()

        //Setting click listeners and call presenter's methods for this
        select_all_checkbox.setOnClickListener { managerActualPurchases.onSelectAllCheckBoxClicked() }

        complete_purchase_btn.setOnClickListener { managerActualPurchases.onCompletePurchaseButtonClicked() }

        add_new_purchase_btn.setOnClickListener { managerActualPurchases.onAddPurchaseButtonClicked() }
    }

    override fun onResume() {
        super.onResume()
        managerActualPurchases.onResume()
    }

    override fun onPause() {
        super.onPause()
        managerActualPurchases.onPause()
    }

    //Setters or views status changers
    // Body view state changers
    override fun swapToFloatingBtnAdd() {
        complete_purchase_btn.hide()
        add_new_purchase_btn.show()
    }

    override fun swapToFloatingBtnComplete() {
        complete_purchase_btn.show()
        add_new_purchase_btn.hide()
    }

    override fun setSelectAllUnchecked() {
        select_all_checkbox.unChecked()
    }

    override fun setEmptyBody() {
        template_progressbar.hideView()
        actual_purchase_content.hideView()
        template_empty_list.showView()
    }

    override fun setContentBody() {
        template_empty_list.hideView()
        template_progressbar.hideView()
        actual_purchase_content.showView()
    }

    override fun setProgressBody() {
        template_empty_list.hideView()
        actual_purchase_content.hideView()
        template_progressbar.showView()
    }

}




