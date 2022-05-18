package com.example.health_diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.health_diary.db.IntentConstTask

class Create_H_W : AppCompatActivity() {


    val DbManager = com.example.health_diary.db.DbManager(this)
    var id = 0
    var isEdit = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_hw)
        getMyIntents()
    }


    fun getMyIntents(){
        val i = intent
        if(i!=null){
            if(i.getStringExtra(IntentConstTask.I_title) != null ){
                isEdit = true
                var et = findViewById<EditText>(R.id.ET_title)
               et.setText(i.getStringExtra(IntentConstTask.I_title))
                id =i.getIntExtra(IntentConstTask.I_id_task,0)
                Log.d("Mylog","------------------------------------проверка id привычки $id")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        DbManager.openDb()
    }
    fun back(view: View){
        val intent = Intent(this, PageCreate::class.java)
        startActivity(intent)
    }

    lateinit var time : String
    var type  = 0

    fun morning(view: View){
        time = "Утро"
        changeTime()
    }

    fun day(view: View){
        time = "День"
        changeTime()
    }

    fun evening(view: View){
        time = "Вечер"
        changeTime()
    }

    fun changeTime(){
        val mor = findViewById<Button>(R.id.b_morning)
        val day = findViewById<Button>(R.id.b_day)
        val eve = findViewById<Button>(R.id.b_evening)

        if(time == "Утро"){
            mor.visibility = View.GONE
            day.visibility = View.VISIBLE
            eve.visibility = View.VISIBLE
        }
        else{
            if(time == "День"){
                mor.visibility = View.VISIBLE
                day.visibility = View.GONE
                eve.visibility = View.VISIBLE
            }
            else{
                if(time == "Вечер"){
                    mor.visibility = View.VISIBLE
                    day.visibility = View.VISIBLE
                    eve.visibility = View.GONE
                }
            }
        }
    }

    var m = 0
    var tu = 0
    var w = 0
    var th = 0
    var f = 0
    var sa = 0
    var su = 0


    fun mon(view: View){
        m = 2
        checkDay()

    }
    fun tue(view: View){
        tu = 2
        checkDay()
    }
    fun wed(view: View){
        w = 2
        checkDay()
    }
    fun thu(view: View){
        th = 2
       checkDay()
    }
    fun fri(view: View){
        f = 2
        checkDay()
    }
    fun sat(view: View){
        sa = 2
        checkDay()
    }
    fun sun(view: View){
        su = 2
        checkDay()
    }



    fun mon2(view: View){
        m = 1
        checkDay()
    }
    fun tue2(view: View){
        tu = 1
        checkDay()
    }
    fun wed2(view: View){
        w =1
        checkDay()
    }
    fun thu2(view: View){
        th =1
        checkDay()
    }
    fun fri2(view: View){
        f = 1
        checkDay()
    }
    fun sat2(view: View){
        sa = 1
        checkDay()
    }
    fun sun2(view: View){
        su = 1
       checkDay()
    }

    fun checkDay(){
        val mon = findViewById<Button>(R.id.b_mon)
        val monb = findViewById<Button>(R.id.b_mon2)
        val tue = findViewById<Button>(R.id.b_tue)
        val tueb = findViewById<Button>(R.id.b_tue2)
        val wed = findViewById<Button>(R.id.b_wed)
        val wedb = findViewById<Button>(R.id.b_wed2)
        val thu = findViewById<Button>(R.id.b_thu)
        val thub = findViewById<Button>(R.id.b_thu2)
        val fri = findViewById<Button>(R.id.b_fri)
        val frib = findViewById<Button>(R.id.b_fri2)
        val sat = findViewById<Button>(R.id.b_sat)
        val satb = findViewById<Button>(R.id.b_sat2)
        val sun = findViewById<Button>(R.id.b_sun)
        val sunb = findViewById<Button>(R.id.b_sun2)

        if (m==2){
            mon.visibility = View.GONE
        }
        else{mon.visibility = View.VISIBLE
            monb.visibility = View.GONE}

        if (tu==2){
            tue.visibility = View.GONE
        }
        else{tue.visibility = View.VISIBLE
            tueb.visibility = View.GONE}

        if (w==2){
            wed.visibility = View.GONE
        }
        else{wed.visibility = View.VISIBLE
            wedb.visibility = View.GONE}

        if (th==2){
            thu.visibility = View.GONE
        }
        else{thu.visibility = View.VISIBLE
            thub.visibility = View.GONE}

        if (f==2){
            fri.visibility = View.GONE
        }
        else{fri.visibility = View.VISIBLE
            frib.visibility = View.GONE}

        if (sa==2){
            sat.visibility = View.GONE
        }
        else{sat.visibility = View.VISIBLE
            satb.visibility = View.GONE}

        if (su==2){
            sun.visibility = View.GONE
        }
        else{sun.visibility = View.VISIBLE
            sunb.visibility = View.GONE}
    }


    fun bworkout(view: View){
        type = 1
        changTask()

    }

    fun bhabit(view: View){
        type = 2
        changTask()

    }

    fun changTask() {
        val but_workout = findViewById<Button>(R.id.b_workout)
        val but_habit = findViewById<Button>(R.id.b_habit)
        if(type == 1){

            but_workout.visibility = View.GONE
            but_habit.visibility = View.VISIBLE
        }
        else{
            if(type == 2){

                but_habit.visibility = View.GONE
                but_workout.visibility = View.VISIBLE
            }
        }

    }

    fun savetask(view: View){


        var et = findViewById<EditText>(R.id.ET_title)

        if(isEdit){
            DbManager.updatetask(et.text.toString(),time,type,1,id)
        }else {
            DbManager.insertToUTask(et.text.toString(), time, type, 1)
        }
        var idS = DbManager.readSequence()

        //insert to execution


        var iii=  idS.toString()
        var itog = iii.substringAfter('[')
        itog = itog.substringBeforeLast(']')
        var itogId = Integer.parseInt(itog)


        if (m==2){
           DbManager.insertToExecution(itogId,"Mon")
        }
        if (tu==2){
            DbManager.insertToExecution(itogId,"Tue")
        }
        if (w==2){
            DbManager.insertToExecution(itogId,"Wed")
        }
        if (th==2){
            DbManager.insertToExecution(itogId,"Thu")
        }
        if (f ==2){
            DbManager.insertToExecution(itogId,"Fri")
        }
        if (sa==2){
            DbManager.insertToExecution(itogId,"Sat")
        }
        if (su==2){
            DbManager.insertToExecution(itogId,"Sun")
        }


        finish()

        val intent = Intent(this, MainMenu::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        DbManager.closeDb()
    }
}