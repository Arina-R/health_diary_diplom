package com.example.health_diary

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationView


class MainMenu : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        val nav_view = findViewById<NavigationView>(R.id.nav_view)
        nav_view.setNavigationItemSelectedListener (this)
    }


    var actglmenu = GlMenu()





    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.id_gl ->{ Toast.makeText(this,"gl menu",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, GlMenu::class.java)
                startActivity(intent)}
            R.id.id_norm -> {Toast.makeText(this,"norma",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, norm_calculation::class.java)
                startActivity(intent)}
            R.id.id_pit -> {Toast.makeText(this,"pitanie",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, PageMenu::class.java)
                startActivity(intent)}
            R.id.id_stat -> {Toast.makeText(this,"statistica",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Statistica::class.java)
                startActivity(intent)}
            R.id.id_workout -> {Toast.makeText(this,"workout",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, PageWorkout::class.java)
                startActivity(intent)}
            R.id.id_habit -> {Toast.makeText(this,"habit",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, PageHabits::class.java)
                startActivity(intent)}
        }

        return true
    }
}