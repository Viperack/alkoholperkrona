package com.example.alkoholperkrona.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductDatabaseDao {
    @Insert
    fun insert(product: Product)

    @Update
    fun update(product: Product)

    @Query("SELECT * FROM product_information_table")
    fun getAll(): Array<Product>

    @Query("SELECT * FROM product_information_table LIMIT 1")
    fun getFirstRow(): Product
}