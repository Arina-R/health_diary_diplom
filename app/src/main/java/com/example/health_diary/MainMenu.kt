package com.example.health_diary

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.health_diary.db.IntentConstTask
import com.google.android.material.navigation.NavigationView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MainMenu : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    val DbManager = com.example.health_diary.db.DbManager(this)
    val tAdapter = com.example.health_diary.db.AdapterTask(ArrayList(),this)




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        val nav_view = findViewById<NavigationView>(R.id.nav_view)
        nav_view.setNavigationItemSelectedListener (this)
        init()


        //одноразовое использование в проге(при первом запуске)
        val settings = ""
        val sp = getSharedPreferences(settings, Context.MODE_PRIVATE)
        // проверяем, первый ли раз открывается программа
        val hasVisited = sp.getBoolean("hasVisited", false)

        if (!hasVisited) {


            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            val e: SharedPreferences.Editor = sp.edit()
            e.putBoolean("hasVisited", true)
            e.commit() // не забудьте подтвердить изменения
        }
    }

    private fun getSwapMg(): ItemTouchHelper {
        return ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return  false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                tAdapter.checkedTask(viewHolder.adapterPosition, DbManager)
                fillAdapter()

            }
        })
    }

    fun init(){

        val rc_main = findViewById<RecyclerView>(R.id.rc_main)


        rc_main.layoutManager = LinearLayoutManager(this)
        val swapHelper = getSwapMg()
        swapHelper.attachToRecyclerView(rc_main)
        rc_main.adapter = tAdapter
        Toast.makeText(this,"${getTime()}", Toast.LENGTH_SHORT).show()


   }

    private fun getTime(): String {
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("EEE", Locale.getDefault())
        return formatter.format(time)

    }


    fun fillAdapter(){
        tAdapter.updateAdapter(DbManager.readExecutionData(getTime()))
    }

    fun createTask(view: View){
        val intent = Intent(this, Create_H_W::class.java)
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

    override fun onDestroy() {
        super.onDestroy()
        DbManager.closeDb()
    }


    override fun onResume() {
        super.onResume()
        DbManager.openDb()
       fillAdapter()
    }
}