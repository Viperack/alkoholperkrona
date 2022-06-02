package com.example.alkoholperkrona.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_information_table")
data class Product(
    @PrimaryKey(autoGenerate = false)
    var productId: String = "",

    @ColumnInfo(name = "productNumber")
    val productNumber: String = "",

    @ColumnInfo(name = "productNameBold")
    val productNameBold: String = "",

    @ColumnInfo(name = "productNameThin")
    val productNameThin: String = "",

    @ColumnInfo(name = "category")
    val category: String? = "",

    @ColumnInfo(name = "productNumberShort")
    val productNumberShort: String = "",

    @ColumnInfo(name = "producerName")
    val producerName: String = "",

    @ColumnInfo(name = "supplierName")
    val supplierName: String = "",

    @ColumnInfo(name = "bottleText")
    val bottleText: String = "",

    @ColumnInfo(name = "isOrganic")
    val isOrganic: Boolean = false,

    @ColumnInfo(name = "isSustainableChoice")
    val isSustainableChoice: Boolean = false,

    @ColumnInfo(name = "productLaunchDate")
    val productLaunchDate: String = "",

    @ColumnInfo(name = "alcoholPercentage")
    val alcoholPercentage: Double = -1.0,

    @ColumnInfo(name = "volumeText")
    val volumeText: String = "",

    @ColumnInfo(name = "volume")
    val volume: Double = -1.0,

    @ColumnInfo(name = "price")
    val price: Double = -1.0,

    @ColumnInfo(name = "country")
    val country: String = "",

    @ColumnInfo(name = "isDiscontinued")
    val isDiscontinued: Boolean = false,


    )