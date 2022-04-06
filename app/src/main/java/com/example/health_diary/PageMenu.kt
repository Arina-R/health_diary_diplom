package com.example.health_diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class PageMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_menu)
    }
    fun createPitanie(view: View){
        val intent = Intent(this, CreateMenu::class.java)
        startActivity(intent)
    }
}