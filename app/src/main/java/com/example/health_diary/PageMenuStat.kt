package com.example.health_diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PageMenuStat : AppCompatActivity() {

    val DbManager = com.example.health_diary.db.DbManager(this)
    val brAdapter = com.example.health_diary.db.AdapterMenu(ArrayList(),this)
    val luAdapter = com.example.health_diary.db.AdapterMenu(ArrayList(),this)
    val diAdapter = com.example.health_diary.db.AdapterMenu(ArrayList(),this)
    val snAdapter = com.example.health_diary.db.AdapterMenu(ArrayList(),this)
    var curdate = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_menu_stat)
        init()
         curdate = intent.getStringExtra("Date").toString()
        var tvtit = findViewById<TextView>(R.id.tv_menuondate)
        tvtit.text =curdate
        Log.d("Mylog", "curdate VTYU date $curdate")
    }


    fun init(){

        val rc_menuB = findViewById<RecyclerView>(R.id.rc_breakfastmenuS)
        rc_menuB.layoutManager = LinearLayoutManager(this)
        rc_menuB.adapter = brAdapter

        val rc_menuL = findViewById<RecyclerView>(R.id.rc_lunchmenuS)
        rc_menuL.layoutManager = LinearLayoutManager(this)
        rc_menuL.adapter = luAdapter

        val rc_menuD = findViewById<RecyclerView>(R.id.rc_dinnerfmenuS)
        rc_menuD.layoutManager = LinearLayoutManager(this)
        rc_menuD.adapter = diAdapter

        val rc_menuS = findViewById<RecyclerView>(R.id.rc_snackmenuS)
        rc_menuS.layoutManager = LinearLayoutManager(this)
        rc_menuS.adapter = snAdapter

    }


    fun fillAdapter(){

        brAdapter.updateAdapter(DbManager.readMenuBreakfastData(curdate))
        luAdapter.updateAdapter(DbManager.readMenulunchData(curdate))
        diAdapter.updateAdapter(DbManager.readMenuDinnerData(curdate))
        snAdapter.updateAdapter(DbManager.readMenuSnackData(curdate))

    }
    fun back(view: View){
        val intent = Intent(this, Nav_Stat::class.java)
        startActivity(intent)
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