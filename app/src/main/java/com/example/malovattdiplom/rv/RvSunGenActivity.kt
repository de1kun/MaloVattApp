package com.example.malovattdiplom.rv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.malovattdiplom.R
import com.example.malovattdiplom.RecyclerViewAdapter

class RvSunGenActivity : AppCompatActivity() {


    private var titleslist = mutableListOf <String>()
    private var descList = mutableListOf <String>()
    private var imagesList = mutableListOf <Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_sun_gen)

        val wind_recyclerView: RecyclerView = findViewById(R.id.sun_gen_view)


        postToList()

        wind_recyclerView.layoutManager = LinearLayoutManager(this)
        wind_recyclerView.adapter = RecyclerViewAdapter(titleslist, descList, imagesList)
    }
    private fun addToList (title: String, description: String, image: Int) {
        titleslist.add(title)
        descList.add(description)
        imagesList.add (image)
    }
    private fun postToList() {

        addToList("Монокристаллические", "5-10 Вт", R.drawable.mono)
        addToList("Поликристаллические", "10-15 Вт", R.drawable.poly)
        addToList("Тонкоплёночные", "до 40 Вт", R.drawable.plenk)
    }
}