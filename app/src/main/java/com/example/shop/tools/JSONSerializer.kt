package com.example.shop.tools

import android.content.Context
import android.util.Log
import com.example.shop.ShopItem
import org.json.JSONArray
import org.json.JSONTokener
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.Writer

class JSONSerializer(private val context: Context, private val fileName: String) {
    fun saveItems(items: ArrayList<ShopItem>){
        val array = JSONArray()
        for(item in items){
            array.put(item.getAsJSON())
        }

        var writer: Writer? = null
        try {
            val out = context.openFileOutput(fileName, Context.MODE_PRIVATE)
            writer = OutputStreamWriter(out)
            Log.d("TEST", "Saving... ${array.toString()}")
            writer.write(array.toString())
        }
        catch (e:Exception){
            Log.d("TEST", e.message.toString())
        }
        finally {
            writer?.close()
        }
    }
    fun loadItems(): ArrayList<ShopItem>{
        val items = ArrayList<ShopItem>()
        var reader: BufferedReader? = null
        try {
            val inputStream = context.openFileInput(fileName)
            reader = BufferedReader(InputStreamReader(inputStream))
            val jsonString = StringBuilder()
            var line: String? = null

            while (reader.readLine().also { line = it } != null) {
                jsonString.append(line)
            }
            val array = JSONTokener(jsonString.toString()).nextValue() as JSONArray
            for (i in 0 until array.length()) {
                items.add(ShopItem(array.getJSONObject(i)))
            }
        }
        catch (e: FileNotFoundException){
            Log.e("TEST", e.message.toString())
        }
        finally {
            reader?.close()
        }
        return items
    }
}