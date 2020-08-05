package com.kozitskiy.shoppinglist.adapters.model

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable

//This data class for working with purchases objects in the adapter and the current running app time
//Parcelable to transferring data between fragments using Intent
data class DataPurchaseItemView(
    val uniqueId: Long,
    val bitmapImg: Bitmap? = null,
    val name: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readParcelable(Bitmap::class.java.classLoader),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(uniqueId)
        parcel.writeParcelable(bitmapImg, flags)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataPurchaseItemView> {
        override fun createFromParcel(parcel: Parcel): DataPurchaseItemView {
            return DataPurchaseItemView(
                parcel
            )
        }

        override fun newArray(size: Int): Array<DataPurchaseItemView?> {
            return arrayOfNulls(size)
        }
    }
}