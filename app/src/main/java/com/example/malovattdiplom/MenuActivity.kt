package com.example.malovattdiplom

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.malovattdiplom.data.Item
import com.example.malovattdiplom.data.ItemViewModel


class MenuActivity : AppCompatActivity(), RowInterface, PriceRefund {


    private lateinit var set_button : AppCompatButton
    private lateinit var add_settings_btn: Button
    private lateinit var close_settings_btn: Button
    private lateinit var pref : SharedPreferences
    private lateinit var mItemViewModel: ItemViewModel
    private lateinit var item_name: EditText
    private lateinit var vatt_count: EditText
    private lateinit var price: EditText
    private lateinit var add_item_btn: Button
    private lateinit var close_window_btn: Button
    private lateinit var mAlertDialog: AlertDialog
    private lateinit var delete_item_btn: AppCompatButton
    private var mainCount = 0
    private var vattCount = 0
    private var itemList = emptyList<Item>()

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val button= findViewById<AppCompatButton>(R.id.add_btn)
        val priceMenu = findViewById<TextView>(R.id.textView9)
        val vattMenu = findViewById<TextView>(R.id.textView8)
        mItemViewModel = ViewModelProvider(this)[ItemViewModel::class.java]
        pref = applicationContext.getSharedPreferences("MyPref", 0) // 0 - for private mode

        val adapter = ListAdapter(this, this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        mItemViewModel.readAllData.observe(this, Observer{item ->
            adapter.setData(item)
            for (i in item.indices){
                mainCount += item[i].vatt_count
                vattCount += (item[i].vatt_count * pref.getInt("key_price", -1))
            }
            priceMenu.text = "Общие затраты :$mainCount"
            vattMenu.text = "Ватт используется :$vattCount"
        })

    Log.d("sp",pref.getInt("key_price", -1).toString())
        button.setOnClickListener{
            if (!pref.getInt("key_price", -1).toString().contains("-1")&&!pref.getInt("key_price", -1).toString().contains("0")){
                val mDialogView = LayoutInflater.from(this).inflate(R.layout.add_dialoge_item, null)
                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                item_name = mDialogView.findViewById(R.id.item_name)
                vatt_count = mDialogView.findViewById(R.id.vatt_count)
                mAlertDialog = mBuilder.show()
                mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                add_item_btn= mDialogView.findViewById<AppCompatButton>(R.id.add_item_btn)
                add_item_btn.setOnClickListener{
                    insertDataToDatabase()
                    vattCount = 0
                    mAlertDialog.dismiss()
                }
                close_window_btn=mDialogView.findViewById<AppCompatButton>(R.id.close_window_btn)
                close_window_btn.setOnClickListener{
                    mAlertDialog.dismiss()
                }
            }
            else{
                val alert = AlertDialog.Builder(this).setTitle("Ошибка").setMessage("Добавьте данные в настройки")
                    .setPositiveButton("ok", null)
                alert.show()
            }

        }

        //настройки
        val editor: SharedPreferences.Editor = pref.edit()
        val set_button = findViewById<Button>(R.id.set_button)
        set_button.setOnClickListener{
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialgue_settings, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
            price = mDialogView.findViewById(R.id.price)
            mAlertDialog = mBuilder.show()
            mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            add_settings_btn= mDialogView.findViewById<AppCompatButton>(R.id.add_settings_btn)
            add_settings_btn.setOnClickListener{
                editor.putInt("key_price", price.text.toString().toInt()).commit() // Storing integer
                mAlertDialog.dismiss()
            }
            close_settings_btn=mDialogView.findViewById<AppCompatButton>(R.id.close_settings_btn)
            close_settings_btn.setOnClickListener{
                mAlertDialog.dismiss()
            }
        }
    }

    private fun insertDataToDatabase() {
        val firstItem = item_name.text.toString()
        val secondVatt = vatt_count.text
        val thirdPrice = pref.getInt("key_price", -1)

        if(inputCheck(firstItem, secondVatt, thirdPrice)){
            //Create Item object
            val item = Item(0, firstItem, Integer.parseInt(secondVatt.toString()),Integer.parseInt(thirdPrice.toString()))
            //add data to database
            mItemViewModel.addItem(item)
            Toast.makeText(this, "Успешно добавлено.", Toast.LENGTH_LONG).show()
            //Navigate back
                mAlertDialog.dismiss()
        }else{
            Toast.makeText(this, "Пожалуйста заполните все поля.", Toast.LENGTH_LONG).show()
       }
    }
    private fun inputCheck(firstItem: String, secondVatt: Editable, thirdPrice: Int): Boolean {
        return !(TextUtils.isEmpty(firstItem) && secondVatt.isEmpty() && thirdPrice.toString().isEmpty())
    }


    @SuppressLint("MissingInflatedId")
    override fun fun1 (a:String, b:String, c:String, d:String ) {
        if (a.isNotEmpty() && b.isNotEmpty() && c.isNotEmpty() && d.isNotEmpty()){
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.add_dialoge_item, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
            item_name = mDialogView.findViewById(R.id.item_name)
            vatt_count = mDialogView.findViewById(R.id.vatt_count)
            price = mDialogView.findViewById(R.id.price)
            mAlertDialog = mBuilder.show()
            item_name.setText(b)
            vatt_count.setText(c)
            price.setText(d)
            mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            add_item_btn= mDialogView.findViewById<AppCompatButton>(R.id.add_item_btn)
            delete_item_btn= mDialogView.findViewById<AppCompatButton>(R.id.delete_item_btn)
            delete_item_btn.isVisible = true
            delete_item_btn.setOnClickListener {
                deleteItem(a,b,c,d)
            }
            add_item_btn.setOnClickListener{
                updateItem(a, item_name.text.toString(),vatt_count.text.toString(),price.text.toString())
                mAlertDialog.dismiss()
            }
            close_window_btn=mDialogView.findViewById<AppCompatButton>(R.id.close_window_btn)
            close_window_btn.setOnClickListener{
                mAlertDialog.dismiss()
            }
        }
    }
    @SuppressLint("SuspiciousIndentation")
    private fun updateItem (a:String, b:String, c:String, d:String ){
        if (b.isNotEmpty() && c.isNotEmpty() && d.isNotEmpty()){
            vattCount = 0
            mainCount = 0
                mItemViewModel.updateItem(Item(a.toInt(),b,c.toInt(),d.toInt()))
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.delete_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllItem() {
        val builder = AlertDialog.Builder(this)
        vattCount = 0
        mainCount = 0
        builder.setPositiveButton("Да") {_,_->
            mItemViewModel.deleteAllItems()
            Toast.makeText(
                this,
                "Всё удалено",
                Toast.LENGTH_SHORT).show()

        }
        builder.setNegativeButton("Нет") {_,_->}
        builder.setTitle("Удалить всё ?")
        builder.setMessage("Вы уверенны что хотите удалить всё ?")
        builder.show()
        }

    private fun deleteItem(a:String, b:String, c:String, d:String ) {
        val builder = AlertDialog.Builder(this)
        vattCount = 0
        mainCount = 0
        builder.setPositiveButton("Да") {_,_->
            Toast.makeText(
                this,
                "Предмет удален",
                Toast.LENGTH_SHORT).show()
            mItemViewModel.deleteItem(Item(a.toInt(),b,c.toInt(),d.toInt()))
            mAlertDialog.dismiss()
        }
        builder.setNegativeButton("Нет") {_,_->}
        builder.setTitle("Удалить предмет ?")
        builder.setMessage("Вы уверены что хотите удалить предмет ?")
        builder.show()


    }

    override fun refund(): Int {
        return pref.getInt("key_price", -1)
    }


}