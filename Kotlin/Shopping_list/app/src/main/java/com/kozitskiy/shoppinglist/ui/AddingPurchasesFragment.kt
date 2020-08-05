package com.kozitskiy.shoppinglist.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kozitskiy.shoppinglist.*
import com.kozitskiy.shoppinglist.interfaces.PurchaseReceivers
import com.kozitskiy.shoppinglist.management.ManagerAddingPurchases
import com.kozitskiy.shoppinglist.utils.showForShortTime
import kotlinx.android.synthetic.main.fragment_add_purchases.*
import javax.inject.Inject

class AddingPurchasesFragment @Inject constructor() : Fragment(),
    PurchaseReceivers.ViewAddingPurchaseReceiver {

    @Inject
    lateinit var managerPurchaseAdding: ManagerAddingPurchases

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_add_purchases, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        App.appComponent.inject(this)

        managerPurchaseAdding.onViewCreated()

        //Setting click listeners and call presenter's methods for this
        btn_upload_image.setOnClickListener { managerPurchaseAdding.uploadImageClicked() }

        btn_add_purchase.setOnClickListener { managerPurchaseAdding.addPurchaseClicked() }
    }

    //Getting onActivityResult and transfer to presenter
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        managerPurchaseAdding.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        managerPurchaseAdding.onRequestPermissionsResult(requestCode, grantResults)
    }

    //Setters or views status changers
    override fun setAddedImageView(bitmap: Bitmap) {
        purchase_adding_img.setImageBitmap(bitmap)
    }

    override fun showPurchaseSuccessMsg() {
        purchase_result_msg.setTextColor(Color.GREEN);
        purchase_result_msg.showForShortTime()
    }

    override fun showPurchaseErrorMsg() {
        purchase_result_msg.setTextColor(Color.RED);
        purchase_result_msg.showForShortTime()
    }

    override fun clearPurchaseAddingFields() {
        purchase_adding_img.setImageResource(DEFAULT_PURCHASE_IMAGE)
        purchase_adding_name.setText("")
    }

    //Call onResume/onPause in presenter
    override fun onResume() {
        super.onResume()
        managerPurchaseAdding.onResume()
    }

    override fun onPause() {
        super.onPause()
        managerPurchaseAdding.onPause()
    }
}




