package com.example.health_diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

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
        val intent = Intent(this, MainMenu::class.java)
        startActivity(intent)
    }
}