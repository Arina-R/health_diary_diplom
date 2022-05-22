package com.example.health_diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class AddPosition : AppCompatActivity() {

    val DbManager = com.example.health_diary.db.DbManager(this)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_position)

    }

    fun savePosition(view: View){

        var etName = findViewById<EditText>(R.id.ET_positionName)
        var etCalories = findViewById<EditText>(R.id.ET_positionCalories)
        var Cal = 0
        var lay = findViewById<ConstraintLayout>(R.id.errorPanel)



        if(etName.text.toString() == ""|| etName.text.toString() == " " || etCalories.text.toString() == "" || etCalories.text.toString() == " "){

            lay.visibility = View.VISIBLE

        }
        else {

                try {
                    Cal =Integer.parseInt(etCalories.getText().toString())
                    DbManager.insertToFood(etName.text.toString(), Cal)
                    val intent = Intent(this, PageFood::class.java)
                    startActivity(intent)
                } catch (e: Exception) {
                    lay.visibility = View.VISIBLE

                }

            }


    }

    fun hide(view: View){
        var lay = findViewById<ConstraintLayout>(R.id.errorPanel)
        lay.visibility = View.GONE

        var etName = findViewById<EditText>(R.id.ET_positionName)
        var etCalories = findViewById<EditText>(R.id.ET_positionCalories)
        etName.setText("")
        etCalories.setText("")
    }

    override fun onResume() {
        super.onResume()
        DbManager.openDb()
    }

    override fun onDestroy() {
        super.onDestroy()
        DbManager.closeDb()
    }

    fun back(view: View){
        val intent = Intent(this, PageFood::class.java)
        startActivity(intent)
    }
}