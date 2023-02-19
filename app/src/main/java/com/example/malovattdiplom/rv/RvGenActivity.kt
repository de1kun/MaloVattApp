package com.example.malovattdiplom.rv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.malovattdiplom.R
import com.example.malovattdiplom.RecyclerViewAdapter

class RvGenActivity : AppCompatActivity() {

    private var titleslist = mutableListOf <String>()
    private var descList = mutableListOf <String>()
    private var imagesList = mutableListOf <Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_gen)

        val gen_recyclerView: RecyclerView = findViewById(R.id.gen_view)


        postToList()

        gen_recyclerView.layoutManager = LinearLayoutManager(this)
        gen_recyclerView.adapter = RecyclerViewAdapter(titleslist, descList, imagesList)
    }
    private fun addToList (title: String, description: String, image: Int) {
        titleslist.add(title)
        descList.add(description)
        imagesList.add (image)
    }
    private fun postToList() {

        addToList("Бытовые", "5-7 кВт", R.drawable.gen1)
        addToList("Промышленные", "15-25 кВт", R.drawable.gen2)
        addToList("Реактивные", "до 40 Вт", R.drawable.gen3)
    }

}