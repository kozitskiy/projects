package com.kozitskiy.shoppinglist.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.kozitskiy.shoppinglist.cache.db.model.DataPurchaseItemDb
import com.kozitskiy.shoppinglist.adapters.model.DataPurchaseItemView
import java.io.ByteArrayOutputStream

object Converters {
    @Throws(IllegalArgumentException::class)
    //Converting Base64 string to Bitmap
    fun convertToBitmap(base64Str: String): Bitmap {
        val decodedBytes = Base64.decode(
            base64Str.substring(base64Str.indexOf(",") + 1),
            Base64.DEFAULT
        )
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }

    //Converting Bitmap to Base64 string
    fun convertToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 30, byteArrayOutputStream)
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT)
    }

    //Converting DB model to View model
    fun convertPurchaseDbListToViewList(purchasesDb: List<DataPurchaseItemDb>): List<DataPurchaseItemView> {
        val purchasesView = ArrayList<DataPurchaseItemView>()
        purchasesDb.forEach {
            val bitmapImg: Bitmap? = it.decodedImg?.let { it1 ->
                convertToBitmap(
                    it1
                )
            }
            purchasesView.apply {
                add(
                    DataPurchaseItemView(
                        it.uniqueId,
                        bitmapImg,
                        it.name
                    )
                )
            }
        }
        return purchasesView
    }

    //Getting list of Purchases Unique Ids (object field) from purchases list
    fun getPurchasesViewUniqueIds(purchasesView: List<DataPurchaseItemView>): List<Long> {
        val purchasesUniqueIds = ArrayList<Long>()
        purchasesView.forEach { purchasesUniqueIds.add(it.uniqueId) }
        return purchasesUniqueIds
    }
}



