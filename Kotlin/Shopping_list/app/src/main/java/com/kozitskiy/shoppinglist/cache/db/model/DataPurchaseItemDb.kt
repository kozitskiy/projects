package com.kozitskiy.shoppinglist.cache.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class DataPurchaseItemDb(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val uniqueId: Long,
    val decodedImg: String? = null,
    val name: String,
    val status: Int = 0
)