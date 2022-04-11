package com.example.health_diary

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class MainMenu : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        val nav_view = findViewById<NavigationView>(R.id.nav_view)
        nav_view.setNavigationItemSelectedListener (this)
    }

    fun createTask(view: View){
        val intent = Intent(this, PageCreate::class.java)
        startActivity(intent)
    }

    fun menuopenCloseNavigationDrawer(view: View) {
        val drawerL = findViewById<DrawerLayout>(R.id.drawerL)
        if (drawerL.isDrawerOpen(GravityCompat.START)) {
            drawerL.closeDrawer(GravityCompat.START)
        } else {
            drawerL.openDrawer(GravityCompat.START)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.id_gl ->{ Toast.makeText(this,"gl menu",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainMenu::class.java)
                startActivity(intent)}
            R.id.id_norm -> {Toast.makeText(this,"norma",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Nav_Norma::class.java)
                startActivity(intent)}
            R.id.id_pit -> {Toast.makeText(this,"pitanie",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Nav_Pitanie::class.java)
                startActivity(intent)}
            R.id.id_stat -> {Toast.makeText(this,"statistica",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Nav_Stat::class.java)
                startActivity(intent)}
            R.id.id_workout -> {Toast.makeText(this,"workout",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Nav_Workout::class.java)
                startActivity(intent)}
            R.id.id_habit -> {Toast.makeText(this,"habit",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Nav_habit::class.java)
                startActivity(intent)}
        }

        return true
    }
}