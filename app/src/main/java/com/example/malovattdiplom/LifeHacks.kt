package com.example.malovattdiplom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class LifeHacks : AppCompatActivity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_hacks)

        val lifeHacksList = ArrayList<String>()
        lifeHacksList.add("1. Гасите свет, переходя из комнаты в комнату. Установите тепловые датчики движения, которые будут выключать свет за вас.")
        lifeHacksList.add("2. Используйте местное освещение: подсветки, торшеры, бра. Например, чтобы каждый раз не включать основные источники света, в комнате лучше установить подсветку из светодиодной ленты.")
        lifeHacksList.add("3. Помните, что чистота — залог экономии. Грязные окна и пыльные плафоны снижают уровень освещённости в помещении до 35%.")
        lifeHacksList.add("4. При ремонте учитывайте, что светлые стены и мебель будут отражать до 80% светового потока, а тёмные — лишь около 12%.")
        lifeHacksList.add("5. Замените лампочки накаливания на энергосберегающие и светодиодные. Замена только одной лампы позволит экономить около 1 000 рублей в год.")
        lifeHacksList.add("6. При отсутствии двухтарифного счётчика электроэнергии отключайте все неосновные электрические приборы на ночь, а зарядные устройства — после полной подпитки техники.")
        lifeHacksList.add("7. Покупайте энергосберегающую бытовую технику!")
        lifeHacksList.add("7. Покупайте энергосберегающую бытовую технику!")
        lifeHacksList.add("7. Покупайте энергосберегающую бытовую технику!")
        lifeHacksList.add("7. Покупайте энергосберегающую бытовую технику!")
        lifeHacksList.add("7. Покупайте энергосберегающую бытовую технику!")
        lifeHacksList.add("7. Покупайте энергосберегающую бытовую технику!")

        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lifeHacksList)
    }
}