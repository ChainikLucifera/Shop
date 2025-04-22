package com.example.shop

class ShopItem(private var name: String) {
    private var imagePath: String? = null

    fun setName(name: String) {
        this.name = name
    }

    fun getName() = this.name

    fun setImagePath(path: String?) {
        this.imagePath = path
    }


// нужно при помощи контракта доставать из галереи фото,
//а потом реализовывать этот метод

//    fun getImagePath(): Uri? {
//        if(imagePath != null)
//            return Uri.parse(imagePath)
//        return null
//    }
}