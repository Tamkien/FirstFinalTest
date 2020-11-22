package com.kienct.firstfinaltest.jsonparsing

import com.google.gson.annotations.SerializedName
import com.kienct.firstfinaltest.jsonparsing.Item

data class ItemList(
    @SerializedName("items")
    var items : List<Item>
)