package com.example.malovattdiplom

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import com.example.malovattdiplom.gridadapter.LanguageAdapter
import com.example.malovattdiplom.gridadapter.LanguageItem
import android.content.Intent
import com.example.malovattdiplom.lampsItem.RvItemsActivty

class EcoMenu : AppCompatActivity(), AdapterView.OnItemClickListener {

    private var titlesList = mutableListOf <String>()
    private var desclist = mutableListOf <String>()
    private var imagesList = mutableListOf<Int>()

    private var gridView:GridView? = null
    private var arrayList:ArrayList<LanguageItem>? = null
    private var languageAdapter: LanguageAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eco_menu)

        gridView = findViewById(R.id.eco_grid)
        arrayList = ArrayList()
        arrayList = setDataList()
        languageAdapter = LanguageAdapter(applicationContext, arrayList!!)
        gridView?.adapter = languageAdapter
        gridView?.onItemClickListener = this
    }

    private fun setDataList():ArrayList<LanguageItem>{
        var arrayList:ArrayList<LanguageItem> = ArrayList()

        arrayList.add(LanguageItem(R.drawable.lamp, "Эко лампы"))
        arrayList.add(LanguageItem(R.drawable.wind,"Ветрогенераторы"))
        arrayList.add(LanguageItem(R.drawable.sun_gen,"Солнечные панели"))
        arrayList.add(LanguageItem(R.drawable.generator,"Генераторы"))


        return arrayList
    }
    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        var languageItem: LanguageItem = arrayList!![p2]

        when (languageItem.name) {
            "Эко лампы" -> { val intent = Intent(this, RvItemsActivty::class.java)
                startActivity(intent)}
            "Ветрогенераторы" -> {val intent = Intent(this, RvWindGenActivity::class.java)
                startActivity(intent)}
            "Солнечные панели"-> {}
            "Генераторы"-> {}
        }
    }

}