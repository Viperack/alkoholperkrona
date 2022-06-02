package com.example.alkoholperkrona


import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.platform.app.InstrumentationRegistry
import com.example.alkoholperkrona.database.Product
import com.example.alkoholperkrona.database.ProductDatabase
import com.example.alkoholperkrona.database.ProductDatabaseDao
import com.google.android.material.navigation.NavigationView
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = ProductDatabase.getInstance(this)
        val productDao = db.productDatabaseDao

        var responseFromAPIJSON: JSONObject

        val client = OkHttpClient()

        val request = Request.Builder()
                .url("https://api-extern.systembolaget.se/sb-api-ecommerce/v1/productsearch/search?")
                .header("Ocp-Apim-Subscription-Key", "cfc702aed3094c86b92d6d4ff7a54c84")
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    response.use {
                        if (!response.isSuccessful) throw IOException("Unexpected code $response")

                        for ((name, value) in response.headers) {
                            println("$name: $value")
                        }

                        var responseFromAPIJSON = JSONObject(response.body!!.string())

                        /*for (i in 0..29) {
                            val product = Product(
                                if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(i).isNull("productId")) responseFromAPIJSON.getJSONArray("products").getJSONObject(i)["productId"] as String else "",

                                if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(i).isNull("productNumber")) responseFromAPIJSON.getJSONArray("products").getJSONObject(i)["productNumber"] as String else "",

                                if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(i).isNull("productNameBold")) responseFromAPIJSON.getJSONArray("products").getJSONObject(i)["productNameBold"] as String else "",

                                if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(i).isNull("productNameThin")) responseFromAPIJSON.getJSONArray("products").getJSONObject(i)["productNameThin"] as String else "",

                                if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(i).isNull("category")) responseFromAPIJSON.getJSONArray("products").getJSONObject(i)["category"] as String else "",

                                if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(i).isNull("productNumberShort")) responseFromAPIJSON.getJSONArray("products").getJSONObject(i)["productNumberShort"] as String else "",

                                if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(i).isNull("producerName")) responseFromAPIJSON.getJSONArray("products").getJSONObject(i)["producerName"] as String else "",

                                if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(i).isNull("supplierName")) responseFromAPIJSON.getJSONArray("products").getJSONObject(i)["supplierName"] as String else "",

                                if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(i).isNull("bottleText")) responseFromAPIJSON.getJSONArray("products").getJSONObject(i)["bottleText"] as String else "",

                                if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(i).isNull("isOrganic")) responseFromAPIJSON.getJSONArray("products").getJSONObject(i)["isOrganic"] as Boolean else false,

                                if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(i).isNull("isSustainableChoice")) responseFromAPIJSON.getJSONArray("products").getJSONObject(i)["isSustainableChoice"] as Boolean else false,

                                if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(i).isNull("productLaunchDate")) responseFromAPIJSON.getJSONArray("products").getJSONObject(i)["productLaunchDate"] as String else "",

                                if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(i).isNull("alcoholPercentage")) responseFromAPIJSON.getJSONArray("products").getJSONObject(i)["alcoholPercentage"] as Double else 0.0,

                                if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(i).isNull("volumeText")) responseFromAPIJSON.getJSONArray("products").getJSONObject(i)["volumeText"] as String else "",

                                if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(i).isNull("volume")) responseFromAPIJSON.getJSONArray("products").getJSONObject(i)["volume"] as Double else 0.0,

                                if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(i).isNull("price")) responseFromAPIJSON.getJSONArray("products").getJSONObject(i)["price"] as Double else 0.0,

                                if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(i).isNull("country")) responseFromAPIJSON.getJSONArray("products").getJSONObject(i)["country"] as String else "",

                                if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(i).isNull("isDiscontinued")) responseFromAPIJSON.getJSONArray("products").getJSONObject(i)["isDiscontinued"] as Boolean else false,


                                )
                        }*/

                        val product = Product(
                            if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(0).isNull("productId")) responseFromAPIJSON.getJSONArray("products").getJSONObject(0)["productId"] as String else "",

                            if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(0).isNull("productNumber")) responseFromAPIJSON.getJSONArray("products").getJSONObject(0)["productNumber"] as String else "",

                            if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(0).isNull("productNameBold")) responseFromAPIJSON.getJSONArray("products").getJSONObject(0)["productNameBold"] as String else "",

                            if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(0).isNull("productNameThin")) responseFromAPIJSON.getJSONArray("products").getJSONObject(0)["productNameThin"] as String else "",

                            if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(0).isNull("category")) responseFromAPIJSON.getJSONArray("products").getJSONObject(0)["category"] as String else "",

                            if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(0).isNull("productNumberShort")) responseFromAPIJSON.getJSONArray("products").getJSONObject(0)["productNumberShort"] as String else "",

                            if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(0).isNull("producerName")) responseFromAPIJSON.getJSONArray("products").getJSONObject(0)["producerName"] as String else "",

                            if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(0).isNull("supplierName")) responseFromAPIJSON.getJSONArray("products").getJSONObject(0)["supplierName"] as String else "",

                            if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(0).isNull("bottleText")) responseFromAPIJSON.getJSONArray("products").getJSONObject(0)["bottleText"] as String else "",

                            if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(0).isNull("isOrganic")) responseFromAPIJSON.getJSONArray("products").getJSONObject(0)["isOrganic"] as Boolean else false,

                            if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(0).isNull("isSustainableChoice")) responseFromAPIJSON.getJSONArray("products").getJSONObject(0)["isSustainableChoice"] as Boolean else false,

                            if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(0).isNull("productLaunchDate")) responseFromAPIJSON.getJSONArray("products").getJSONObject(0)["productLaunchDate"] as String else "",

                            if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(0).isNull("alcoholPercentage")) responseFromAPIJSON.getJSONArray("products").getJSONObject(0)["alcoholPercentage"] as Double else 0.0,

                            if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(0).isNull("volumeText")) responseFromAPIJSON.getJSONArray("products").getJSONObject(0)["volumeText"] as String else "",

                            if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(0).isNull("volume")) responseFromAPIJSON.getJSONArray("products").getJSONObject(0)["volume"] as Double else 0.0,

                            if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(0).isNull("price")) responseFromAPIJSON.getJSONArray("products").getJSONObject(0)["price"] as Double else 0.0,

                            if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(0).isNull("country")) responseFromAPIJSON.getJSONArray("products").getJSONObject(0)["country"] as String else "",

                            if (!responseFromAPIJSON.getJSONArray("products").getJSONObject(0).isNull("isDiscontinued")) responseFromAPIJSON.getJSONArray("products").getJSONObject(0)["isDiscontinued"] as Boolean else false,
                        )
                        //println(product)

                        //productDao.insert(product)
                        val retrievedProduct: Product = productDao.getFirstRow()
                        println(retrievedProduct)
                    }
                } 
            })


}

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        return true
    }
}