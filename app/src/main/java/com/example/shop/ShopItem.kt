package com.example.shop

import android.net.Uri
import androidx.core.net.toUri
import org.json.JSONObject

class ShopItem() {
    private var name: String? = null
    private var index: Int? = null
    private var imageUri: Uri? = null

    constructor(json: JSONObject) : this(){
        name = json.getString(NAME)
        imageUri = Uri.parse(json.getString(URI)) // остновка здесь
        //index = json.getString(INDEX).toInt()
    }



    fun setName(name: String) {
        this.name = name
    }
    fun setIndex(index: Int){
        this.index = index
    }
    fun setUri(uri: Uri){
        this.imageUri = uri
    }

    fun getName(): String? = this.name
    fun getIndex(): Int
    {
        if (index!=null)
            return index!!
        return -10000
    }
    fun getUri(): Uri? = this.imageUri

    fun getAsJSON(): JSONObject {
        val json = JSONObject()
        json.put(NAME, name)
        json.put(URI, imageUri.toString())
        //json.put(INDEX, index)

        return json
    }

    companion object{
        private val NAME = "NAME"
        private val URI = "URI"
        //private val INDEX = "INDEX"
    }
}

// нужно при помощи контракта доставать из галереи фото,
//а потом реализовывать этот метод

