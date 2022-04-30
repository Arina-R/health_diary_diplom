package com.example.health_diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import com.example.health_diary.db.IntentConstHab

class Create_H_W : AppCompatActivity() {


    val DbManager = com.example.health_diary.db.DbManager(this )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_hw)
        getMyIntents()
    }


    fun getMyIntents(){
        val i = intent
        if(i!=null){
            if(i.getStringExtra(IntentConstHab.I_title) != null ){
                var et = findViewById<EditText>(R.id.ET_title)
               et.setText(i.getStringExtra(IntentConstHab.I_title))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        DbManager.openDb()
    }
    fun back(view: View){
        val intent = Intent(this, PageCreate::class.java)
        startActivity(intent)
    }

    lateinit var time : String
    lateinit var week : String
    var type  = 0

    fun morning(view: View){
        time = "Утро"
    }

    fun day(view: View){
        time = "День"
    }

    fun evening(view: View){
        time = "Вечер"
    }

    fun mon(view: View){
        week = "Понедельник"
    }

    fun tue(view: View){
        week = "Вторник"
    }

    fun wed(view: View){
        week = "Среда"
    }

    fun thu(view: View){
        week = "Четверг"
    }

    fun fri(view: View){
        week = "Пятницв"
    }

    fun sut(view: View){
        week = "Суббота"
    }

    fun sun(view: View){
        week = "Воскресенье"
    }

    fun bworkout(view: View){
        type = 1
    }

    fun bhabit(view: View){
        type = 2
    }

    fun savetask(view: View){


        var et = findViewById<EditText>(R.id.ET_title)



        DbManager.insertToUTask(et.text.toString(),time,type,1)


        val intent = Intent(this, MainMenu::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        DbManager.closeDb()
    }
}