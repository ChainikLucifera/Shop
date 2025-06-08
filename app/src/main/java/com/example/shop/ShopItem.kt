package com.example.shop

import org.json.JSONObject

class ShopItem() {
    private var imagePath: String? = null
    private var name: String? = null
    private var index: Int? = null

    constructor(json: JSONObject) : this(){
        name = json.getString(NAME)
        //index = json.getString(INDEX).toInt()
    }



    fun setName(name: String) {
        this.name = name
    }
    fun setIndex(index: Int){
        this.index = index
    }

    fun getName(): String? = this.name
    fun getIndex(): Int
    {
        if (index!=null)
            return index!!
        return -10000
    }

    fun setImagePath(path: String?) {
        this.imagePath = path
    }


    fun getAsJSON(): JSONObject {
        val json = JSONObject()
        json.put(NAME, name)
        //json.put(INDEX, index)

        return json
    }

    companion object{
        private val NAME = "NAME"
        //private val INDEX = "INDEX"
    }
}

// нужно при помощи контракта доставать из галереи фото,
//а потом реализовывать этот метод

