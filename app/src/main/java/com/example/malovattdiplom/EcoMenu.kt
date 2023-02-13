package com.example.malovattdiplom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast

class EcoMenu : AppCompatActivity(), AdapterView.OnItemClickListener {

    private var gridView:GridView? = null
    private var arrayList:ArrayList<LanguageItem>? = null
    private var languageAdapter:LanguageAdapter? = null
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
        var languageItem: LanguageItem = arrayList!!.get(p2)
        Toast.makeText(applicationContext, languageItem.name, Toast.LENGTH_LONG).show()
    }
}