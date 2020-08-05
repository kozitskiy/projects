package com.kozitskiy.shoppinglist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kozitskiy.shoppinglist.*
import com.kozitskiy.shoppinglist.interfaces.PurchaseReceivers
import com.kozitskiy.shoppinglist.management.ManagerCompletedPurchases
import com.kozitskiy.shoppinglist.utils.hideView
import com.kozitskiy.shoppinglist.utils.showView
import kotlinx.android.synthetic.main.fragment_completed_purchases_list.*
import kotlinx.android.synthetic.main.item_empty_list.*
import kotlinx.android.synthetic.main.item_progress.*
import javax.inject.Inject

class CompletedPurchasesFragment @Inject constructor() : Fragment(),
    PurchaseReceivers.OnSetBodyView {

    @Inject
    lateinit var managerCompletedPurchases: ManagerCompletedPurchases

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_completed_purchases_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        App.appComponent.inject(this)

        managerCompletedPurchases.onViewCreated()
    }

    //Body view state changers
    override fun setEmptyBody() {
        completed_purchase_content.hideView()
        template_progressbar.hideView()
        template_empty_list.showView()
    }

    override fun setContentBody() {
        template_empty_list.hideView()
        template_progressbar.hideView()
        completed_purchase_content.showView()
    }

    override fun setProgressBody() {
        completed_purchase_content.hideView()
        template_empty_list.hideView()
        template_progressbar.showView()
    }


    //Call onResume/onDestroy in presenter
    override fun onResume() {
        super.onResume()
        managerCompletedPurchases.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        managerCompletedPurchases.onDestroy()
    }
}