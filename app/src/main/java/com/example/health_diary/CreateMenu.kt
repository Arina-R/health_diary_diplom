package com.example.health_diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.example.health_diary.db.IntentConstFood
import com.example.health_diary.db.IntentConstTask
import java.text.SimpleDateFormat
import java.util.*

class CreateMenu : AppCompatActivity() {

    val DbManager = com.example.health_diary.db.DbManager(this )
    var isEdit = false
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_menu)
        Log.d("Mylog","Time:"+getTime())
        getMyIntents()
    }
    fun savePitanie(view: View){
        var etQuantity = findViewById<EditText>(R.id.ET_quantity)

         DbManager.insertToMenu(1,getTime())
      //  DbManager.insertToMenuFood(1,1,100,timeM)

        val intent = Intent(this, Nav_Pitanie::class.java)
        startActivity(intent)
    }
    fun back(view: View){
        val intent = Intent(this, Nav_Pitanie::class.java)
        startActivity(intent)
    }
    fun addPosition(view: View){
        val intent = Intent(this, AddPosition::class.java)
        startActivity(intent)
    }

    private fun getTime(): String {
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("EEE dd-MM-yy", Locale.getDefault())
        return formatter.format(time)

    }

    fun pagePosition(view: View){
        val intent = Intent(this, PageFood::class.java)
        startActivity(intent)

    }

    override fun onResume() {
        super.onResume()
        DbManager.openDb()
    }

    override fun onDestroy() {
        super.onDestroy()
        DbManager.closeDb()
    }
    fun getMyIntents(){
        val i = intent
        if(i!=null){
            if(i.getStringExtra(IntentConstFood.I_title_food) != null ){
                isEdit = true
                var et = findViewById<EditText>(R.id.ET_quantity2)
                et.setText(i.getStringExtra(IntentConstFood.I_title_food))
                id =i.getIntExtra(IntentConstFood.I_id_food,0)
            }
        }
    }

    lateinit var timeM :String
    fun breakfast(view: View){
        timeM = "Завтрак"
    }
    fun lunch(view: View){
        timeM = "Обед"
    }
    fun dinner(view: View){
        timeM = "Ужин"
    }
    fun snack(view: View){
        timeM = "Перекус"
    }

}