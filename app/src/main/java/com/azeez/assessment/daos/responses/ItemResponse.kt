package com.azeez.assessment.daos.responses

data class ItemResponse(val itemList: ArrayList<Item>)

data class Item(
    val id: Int,
    val name: String,
    val description: String,
    val price: Float,
    val itemRate: Int
)