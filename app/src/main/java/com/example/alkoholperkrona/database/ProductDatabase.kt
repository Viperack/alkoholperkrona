package com.example.alkoholperkrona.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.alkoholperkrona.MainActivity
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

@Database(entities = [Product::class], version = 4, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {

    abstract val productDatabaseDao: ProductDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: ProductDatabase? = null

        fun getInstance(context: MainActivity): ProductDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ProductDatabase::class.java,
                        "product_information_database"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance


                }
                return instance
            }
        }
    }
}