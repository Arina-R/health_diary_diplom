package com.example.health_diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class Nav_Workout : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val DbManager = com.example.health_diary.db.DbManager(this)
    val wAdapter = com.example.health_diary.db.AdapterHabit(ArrayList(),this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_workout)
        val nav_view5 = findViewById<NavigationView>(R.id.nav_view5)
        nav_view5.setNavigationItemSelectedListener (this)
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

    fun init(){
        val rc_workout = findViewById<RecyclerView>(R.id.rc_workout)

        rc_workout.layoutManager = LinearLayoutManager(this)

        val swapHelper = getSwapMg()
        swapHelper.attachToRecyclerView(rc_workout)
        rc_workout.adapter = wAdapter
    }


    fun fillAdapter(){

        wAdapter.updateAdapter(DbManager.readWorkoutData())
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
                wAdapter.removeItem(viewHolder.adapterPosition, DbManager )

            }
        })
    }



    fun workoutopenCloseNavigationDrawer(view: View) {
        val navworkout = findViewById<DrawerLayout>(R.id.navworkout)
        if (navworkout.isDrawerOpen(GravityCompat.START)) {
            navworkout.closeDrawer(GravityCompat.START)
        } else {
            navworkout.openDrawer(GravityCompat.START)
        }
    }

    fun createTask(view: View){
        val intent = Intent(this, PageCreate::class.java)
        startActivity(intent)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.id_gl ->{ Toast.makeText(this,"gl menu", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainMenu::class.java)
                startActivity(intent)}
            R.id.id_norm -> {
                Toast.makeText(this,"norma", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Nav_Norma::class.java)
                startActivity(intent)}
            R.id.id_pit -> {
                Toast.makeText(this,"pitanie", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Nav_Pitanie::class.java)
                startActivity(intent)}
            R.id.id_stat -> {
                Toast.makeText(this,"statistica", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Nav_Stat::class.java)
                startActivity(intent)}
            R.id.id_workout -> {
                Toast.makeText(this,"workout", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Nav_Workout::class.java)
                startActivity(intent)}
            R.id.id_habit -> {
                Toast.makeText(this,"habit", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Nav_habit::class.java)
                startActivity(intent)}
        }

        return true
    }
}