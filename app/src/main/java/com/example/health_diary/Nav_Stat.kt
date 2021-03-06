package com.example.health_diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class Nav_Stat : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val DbManager = com.example.health_diary.db.DbManager(this)
    var currentDate = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_stat)
        val nav_view4 = findViewById<NavigationView>(R.id.nav_view4)
        nav_view4.setNavigationItemSelectedListener (this)


        var calendsr = findViewById<CalendarView>(R.id.calendarView)
        calendsr.setOnDateChangeListener { view, year, month, dayOfMonth ->
            currentDate = "$dayOfMonth.${month + 1}.$year"
            var rez = DbManager.readStaticData(currentDate)
            var count = findViewById<TextView>(R.id.TV_count)
            count.text ="Выполнено задач : " + rez.toString()
        }
    }

    fun click(view: View){
        val intent = Intent(this, PageMenuStat::class.java)
        intent.putExtra("Date","$currentDate")
        Log.d("Mylog", "current date $currentDate")
        startActivity(intent)

    }


    fun statopenCloseNavigationDrawer(view: View) {
        val navstat = findViewById<DrawerLayout>(R.id.navstat)
        if (navstat.isDrawerOpen(GravityCompat.START)) {
            navstat.closeDrawer(GravityCompat.START)
        } else {
            navstat.openDrawer(GravityCompat.START)
        }
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

    override fun onResume() {
        super.onResume()
        DbManager.openDb()
    }

    override fun onDestroy() {
        super.onDestroy()
        DbManager.closeDb()
    }
}