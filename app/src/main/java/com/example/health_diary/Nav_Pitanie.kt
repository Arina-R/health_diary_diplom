package com.example.health_diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Nav_Pitanie : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val DbManager = com.example.health_diary.db.DbManager(this)
    val brAdapter = com.example.health_diary.db.AdapterMenu(ArrayList(),this)
    val luAdapter = com.example.health_diary.db.AdapterMenu(ArrayList(),this)
    val diAdapter = com.example.health_diary.db.AdapterMenu(ArrayList(),this)
    val snAdapter = com.example.health_diary.db.AdapterMenu(ArrayList(),this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_pitanie)
        val nav_view3 = findViewById<NavigationView>(R.id.nav_view3)
        nav_view3.setNavigationItemSelectedListener (this)
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        DbManager.closeDb()
    }


    override fun onResume() {
        super.onResume()
        DbManager.openDb()
        fillAdapter()
    }

    private fun getTime(): String {
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd.M.yyyy", Locale.getDefault())
        return formatter.format(time)


    }

    fun init(){

        val rc_menuB = findViewById<RecyclerView>(R.id.rc_breakfastmenu)
        rc_menuB.layoutManager = LinearLayoutManager(this)
        rc_menuB.adapter = brAdapter

        val rc_menuL = findViewById<RecyclerView>(R.id.rc_lunchmenu)
        rc_menuL.layoutManager = LinearLayoutManager(this)
        rc_menuL.adapter = luAdapter

        val rc_menuD = findViewById<RecyclerView>(R.id.rc_dinnerfmenu)
        rc_menuD.layoutManager = LinearLayoutManager(this)
        rc_menuD.adapter = diAdapter

        val rc_menuS = findViewById<RecyclerView>(R.id.rc_snackmenu)
        rc_menuS.layoutManager = LinearLayoutManager(this)
        rc_menuS.adapter = snAdapter

    }


    fun fillAdapter(){

        brAdapter.updateAdapter(DbManager.readMenuBreakfastData(getTime()))
        luAdapter.updateAdapter(DbManager.readMenulunchData(getTime()))
        diAdapter.updateAdapter(DbManager.readMenuDinnerData(getTime()))
        snAdapter.updateAdapter(DbManager.readMenuSnackData(getTime()))

    }


    fun pitanieopenCloseNavigationDrawer(view: View) {
        val navpit = findViewById<DrawerLayout>(R.id.navpit)
        if (navpit.isDrawerOpen(GravityCompat.START)) {
            navpit.closeDrawer(GravityCompat.START)
        } else {
            navpit.openDrawer(GravityCompat.START)
        }
    }

    fun createPitanie(view: View){
        val intent = Intent(this, CreateMenu::class.java)
        startActivity(intent)
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