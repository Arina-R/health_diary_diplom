package com.example.health_diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PageFood : AppCompatActivity() {

    val DbManager = com.example.health_diary.db.DbManager(this )
    val fAdapter = com.example.health_diary.db.AdapterFood(ArrayList(),this)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_food)
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
        val rc_food = findViewById<RecyclerView>(R.id.rc_food)
        rc_food.layoutManager = LinearLayoutManager(this)
        val swapHelper = getSwapMg()
        swapHelper.attachToRecyclerView(rc_food)
        rc_food.adapter = fAdapter

    }


    fun fillAdapter(){
        fAdapter.updateAdapter(DbManager.readFoodData())

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
                fAdapter.removeItem(viewHolder.adapterPosition, DbManager )

            }
        })
    }

    fun createFood(view: View){
        val intent = Intent(this, AddPosition::class.java)
        startActivity(intent)
    }

    fun backMenu(view: View){
        val intent = Intent(this, CreateMenu::class.java)
        startActivity(intent)
    }

}