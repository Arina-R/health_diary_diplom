package com.example.health_diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class AddPosition : AppCompatActivity() {

    val DbManager = com.example.health_diary.db.DbManager(this )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_position)
    }

    fun savePosition(view: View){

        var etName = findViewById<EditText>(R.id.ET_positionName)
        var etCalories = findViewById<EditText>(R.id.ET_positionCalories)

        var Cal =Integer.parseInt(etCalories.getText().toString())

        DbManager.insertToFood(etName.text.toString(),Cal)
        val intent = Intent(this, CreateMenu::class.java)
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

    fun back(view: View){
        val intent = Intent(this, CreateMenu::class.java)
        startActivity(intent)
    }
}