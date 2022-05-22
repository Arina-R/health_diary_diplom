package com.example.health_diary

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    val DbManager = com.example.health_diary.db.DbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    val wr = "Тренировка"
    val hb = "Привычка"

    fun hideA(view: View){
        var lay = findViewById<ConstraintLayout>(R.id.errorPanelA)
        lay.visibility = View.GONE

        var et = findViewById<EditText>(R.id.EdiTextName)
        et.setText("")

    }

    fun autorization(view: View){

        var et = findViewById<EditText>(R.id.EdiTextName)

        if(et.text.toString() == ""){
            var lay = findViewById<ConstraintLayout>(R.id.errorPanelA)
            lay.visibility = View.VISIBLE

        }
        else {

            DbManager.openDb()
            DbManager.insertToUsers(et.text.toString())
            DbManager.insertToType(wr)
            DbManager.insertToType(hb)

            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        DbManager.closeDb()
    }
}