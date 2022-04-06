package com.example.health_diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class CreateMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_menu)
    }
    fun savePitanie(view: View){
        val intent = Intent(this, PageMenu::class.java)
        startActivity(intent)
    }
    fun back(view: View){
        val intent = Intent(this, PageMenu::class.java)
        startActivity(intent)
    }
    fun addPosition(view: View){
        val intent = Intent(this, AddPosition::class.java)
        startActivity(intent)
    }

}