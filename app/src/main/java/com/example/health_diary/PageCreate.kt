package com.example.health_diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class PageCreate : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_create)
    }
    fun addTask(view: View){
        val intent = Intent(this, Create_H_W::class.java)
        startActivity(intent)
    }
    fun back(view: View){
        val intent = Intent(this, PageCreate::class.java)
        startActivity(intent)
    }
}