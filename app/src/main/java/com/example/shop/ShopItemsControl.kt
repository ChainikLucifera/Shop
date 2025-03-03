package com.example.shop

class ShopItemsControl {
    private val shopItems = arrayListOf<ShopItem>()

    fun getItems() = shopItems
    fun addItem(shopItem: ShopItem) {
        shopItems.add(shopItem)
    }

    fun removeItem(shopItem: ShopItem) {
        shopItems.remove(shopItem)
    }

    fun getShopItem(name: String): ShopItem? {
        for (item in shopItems) {
            if (item.getName() == name)
                return item
        }
        return null
    }


}