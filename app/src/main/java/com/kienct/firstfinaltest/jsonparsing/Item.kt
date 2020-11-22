package com.kienct.firstfinaltest.jsonparsing

import com.google.gson.annotations.SerializedName

class Item(
    @SerializedName("name")
    var name: String,
    @SerializedName("image")
    var image: String,
    @SerializedName("checked")
    var checked: Boolean
)