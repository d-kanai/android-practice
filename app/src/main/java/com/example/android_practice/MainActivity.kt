package com.example.android_practice

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val data = arrayOf("Coverage", "Long Method")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
        val listView = findViewById<ListView>(R.id.dod_list_view)
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            val item = (view.findViewById<TextView>(android.R.id.text1)).text
            Toast.makeText(applicationContext, item, Toast.LENGTH_SHORT).show()
        }
    }

}
