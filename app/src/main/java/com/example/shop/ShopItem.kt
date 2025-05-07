package com.example.shop

import org.json.JSONObject

class ShopItem() {
    private var imagePath: String? = null
    private var name: String? = null

    constructor(json: JSONObject) : this(){
        name = json.getString(NAME)
    }



    fun setName(name: String) {
        this.name = name
    }

    fun getName(): String? = this.name

    fun setImagePath(path: String?) {
        this.imagePath = path
    }

    fun getAsJSON(): JSONObject {
        val json = JSONObject()
        json.put(NAME, name)

        return json
    }

    companion object{
        private val NAME = "NAME"
    }
}

// нужно при помощи контракта доставать из галереи фото,
//а потом реализовывать этот метод

