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

class Nav_habit : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    val DbManager = com.example.health_diary.db.DbManager(this)
    val hAdapter = com.example.health_diary.db.AdapterHabit(ArrayList(),this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_habit)
        val nav_view1 = findViewById<NavigationView>(R.id.nav_view1)
        nav_view1.setNavigationItemSelectedListener (this)
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
        val rc_habit = findViewById<RecyclerView>(R.id.rc_habit)

        rc_habit.layoutManager = LinearLayoutManager(this)

        val swapHelper = getSwapMg()
        swapHelper.attachToRecyclerView(rc_habit)
        rc_habit.adapter = hAdapter
    }


    fun fillAdapter(){

        hAdapter.updateAdapter(DbManager.readHabitData())
    }

    private fun getSwapMg(): ItemTouchHelper{
        return ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return  false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                hAdapter.removeItem(viewHolder.adapterPosition, DbManager )

            }
        })
    }

    fun createTask(view: View){
        val intent = Intent(this, PageCreate::class.java)
        startActivity(intent)
    }

    fun habitopenCloseNavigationDrawer(view: View) {
        val navhabit = findViewById<DrawerLayout>(R.id.navhabit)
        if (navhabit.isDrawerOpen(GravityCompat.START)) {
            navhabit.closeDrawer(GravityCompat.START)
        } else {
            navhabit.openDrawer(GravityCompat.START)
        }
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