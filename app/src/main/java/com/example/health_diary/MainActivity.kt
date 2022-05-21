package com.example.health_diary

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    val DbManager = com.example.health_diary.db.DbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    val wr = "Тренировка"
    val hb = "Привычка"


    fun autorization(view: View){

        var et = findViewById<EditText>(R.id.EdiTextName)


       DbManager.openDb()
      DbManager.insertToUsers(et.text.toString())
        DbManager.insertToType(wr)
        DbManager.insertToType(hb)

//вывод в текст вью
/* val dataList = DbManager.readTypeData()
        for(item in dataList){
            tv.append(item)
        }

     val intent = Intent(this, MainMenu::class.java)
        startActivity(intent)*/
    }



    override fun onDestroy() {
        super.onDestroy()
        DbManager.closeDb()
    }
}