package com.kienct.firstfinaltest.jsonparsing

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.kienct.firstfinaltest.R
import com.kienct.firstfinaltest.datetimepicker.DateTimeActivity
import kotlinx.android.synthetic.main.activity_main.*

class JsonParsingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val itemList = listOf(
//            Item("a", "a.png", false),
//            Item("b", "b.png", true),
//            Item("c", "c.png", true),
//            Item("d", "d.png", false),
//            Item("a", "a.png", false),
//            Item("b", "b.png", true),
//            Item("c", "c.png", true),
//            Item("a", "a.png", false),
//            Item("b", "b.png", true),
//            Item("c", "c.png", true),
//            Item("a", "a.png", false),
//            Item("b", "b.png", true),
//            Item("c", "c.png", true),
//            Item("a", "a.png", false),
//            Item("b", "b.png", true),
//            Item("c", "c.png", true),
//            Item("a", "a.png", false),
//            Item("b", "b.png", true),
//            Item("c", "c.png", true),
//            Item("a", "a.png", false),
//            Item("b", "b.png", true),
//            Item("c", "c.png", true),
//            Item("a", "a.png", false),
//            Item("b", "b.png", true),
//            Item("c", "c.png", true)
//        )
//        val itemString = GsonBuilder().setPrettyPrinting().create().toJson(itemList)
//

        val jsonFileString: String = Utils.getJsonFromAssets(
            applicationContext, "data.json"
        )

        val gson = Gson()
        val items = gson.fromJson(jsonFileString, ItemList::class.java)
        val itemAdapter = ItemAdapter(items.items)
        itemView.layoutManager = GridLayoutManager(this, 2)
        itemView.adapter = itemAdapter
        //next screen
        btNext1.setOnClickListener {
            val intent = Intent(this, DateTimeActivity::class.java)
            startActivity(intent)
        }
    }
}