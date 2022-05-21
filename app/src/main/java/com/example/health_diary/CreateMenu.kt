package com.example.health_diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.health_diary.db.IntentConstFood
import java.text.SimpleDateFormat
import java.util.*

class CreateMenu : AppCompatActivity() {

    val DbManager = com.example.health_diary.db.DbManager(this)
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
        var quant = Integer.parseInt(etQuantity.getText().toString())

         DbManager.insertToMenu(1,id,quant,eat,getTime())

        val intent = Intent(this, Nav_Pitanie::class.java)
        startActivity(intent)
    }
    fun back(view: View){
        val intent = Intent(this, Nav_Pitanie::class.java)
        startActivity(intent)
    }

    lateinit var eat : String

    fun breakfast(view: View){
        eat = "Завтрак"
        changeTime()

    }
    fun lunch(view: View){
        eat = "Обед"
        changeTime()
    }
    fun dinner(view: View){
        eat = "Ужин"
        changeTime()
    }
    fun sneck(view: View){
        eat = "Перекус"
        changeTime()
    }

    fun changeTime(){
        val br = findViewById<Button>(R.id.b_breakfast)
        val lun = findViewById<Button>(R.id.b_lunch)
        val din = findViewById<Button>(R.id.b_dinner)
        val sn = findViewById<Button>(R.id.b_snack)

        if(eat == "Завтрак"){
            br.visibility = View.GONE
            lun.visibility = View.VISIBLE
            din.visibility = View.VISIBLE
            sn.visibility = View.VISIBLE
        }
        else{
            if(eat == "Обед"){
                br.visibility = View.VISIBLE
                lun.visibility = View.GONE
                din.visibility = View.VISIBLE
                sn.visibility = View.VISIBLE
            }
            else{
                if(eat == "Ужин"){
                    br.visibility = View.VISIBLE
                    lun.visibility = View.VISIBLE
                    din.visibility = View.GONE
                    sn.visibility = View.VISIBLE
                }
                else {
                    if (eat == "Перекус") {
                        br.visibility = View.VISIBLE
                        lun.visibility = View.VISIBLE
                        din.visibility = View.VISIBLE
                        sn.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun getTime(): String {
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd.M.yyyy", Locale.getDefault())
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


}