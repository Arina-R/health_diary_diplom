package com.example.health_diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class Nav_Norma : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_norma)
        val nav_view2 = findViewById<NavigationView>(R.id.nav_view2)
        nav_view2.setNavigationItemSelectedListener (this)
    }

    fun normaopenCloseNavigationDrawer(view: View) {
        val navnorma = findViewById<DrawerLayout>(R.id.navnorma)
        if (navnorma.isDrawerOpen(GravityCompat.START)) {
            navnorma.closeDrawer(GravityCompat.START)
        } else {
            navnorma.openDrawer(GravityCompat.START)
        }
    }

    var pol = 0
    var Rost = 1
    var Ves = 1
    var Age =1
    var rr =0

    fun male(view: View){
        pol=1
    }
    fun female(view: View){
        pol=2
    }

    fun norma(view: View){
        val rost = findViewById<EditText>(R.id.ET_rost)
        Rost = Integer.parseInt(rost.getText().toString())
        val ves = findViewById<EditText>(R.id.ET_ves)
        Ves = Integer.parseInt(ves.getText().toString())
        val age =findViewById<TextView>(R.id.ET_age)
        Age = Integer.parseInt(age.getText().toString())

        val rez= findViewById<TextView>(R.id.TV_rez)
        if(pol==1){
            rr = 10 * Ves +6*Rost-5*Age +5
        }
        if(pol==2){
             rr = 10 * Ves +6*Rost-5*Age -161
        }
        rez.text = "$rr ккал в день"
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.id_gl ->{ val intent = Intent(this, MainMenu::class.java)
                startActivity(intent)}
            R.id.id_norm -> {val intent = Intent(this, Nav_Norma::class.java)
                startActivity(intent)}
            R.id.id_pit -> {val intent = Intent(this, Nav_Pitanie::class.java)
                startActivity(intent)}
            R.id.id_stat -> {val intent = Intent(this, Nav_Stat::class.java)
                startActivity(intent)}
            R.id.id_workout -> {val intent = Intent(this, Nav_Workout::class.java)
                startActivity(intent)}
            R.id.id_habit -> {val intent = Intent(this, Nav_habit::class.java)
                startActivity(intent)}
        }

        return true
    }
}