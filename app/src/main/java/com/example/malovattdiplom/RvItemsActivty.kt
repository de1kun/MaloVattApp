package com.example.malovattdiplom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RvItemsActivty : AppCompatActivity() {

    private var titleslist = mutableListOf <String>()
    private var descList = mutableListOf <String>()
    private var imagesList = mutableListOf <Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_items_activty)

        val rv_recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.lamp_view)

        postToList()


        rv_recyclerView.layoutManager = LinearLayoutManager(this)
        rv_recyclerView.adapter = RecyclerViewAdapter(titleslist, descList, imagesList)
    }
    private fun addToList (title: String, description: String, image: Int) {
        titleslist.add(title)
        descList.add(description)
        imagesList.add (image)
    }
    private fun postToList() {

        addToList("Лампа 1", "123", R.drawable.ic_launcher_foreground)
        addToList("Лампа 1", "123", R.drawable.ic_launcher_foreground)
        addToList("Лампа 1", "123", R.drawable.ic_launcher_foreground)
        addToList("Лампа 1", "123", R.drawable.ic_launcher_foreground)


    }

}
