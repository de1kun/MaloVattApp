package com.example.malovattdiplom

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen


class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        val start_btn = findViewById<AppCompatButton>(R.id.start_btn)
        start_btn.setOnClickListener{
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)}

        val economy_btn = findViewById<AppCompatButton>(R.id.economy_btn)
        economy_btn.setOnClickListener{
            val intent1 = Intent(this, EcoMenu::class.java)
            startActivity(intent1)
        }

        val lifehack_btn = findViewById<AppCompatButton>(R.id.lifehack_btn)
        lifehack_btn.setOnClickListener{
            val intent2 = Intent(this, LifeHacks::class.java)
            startActivity(intent2)
        }
    }
}