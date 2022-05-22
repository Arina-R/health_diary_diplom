package com.example.health_diary.db

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.health_diary.Create_H_W
import com.example.health_diary.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AdapterTask(listTask:ArrayList<ListExecution>,contextT: Context): RecyclerView.Adapter<AdapterTask.Holder>() {

    var listArray =listTask
    var context = contextT

    class Holder(itemView: View, contextVH: Context) : RecyclerView.ViewHolder(itemView) {
        val DbManager = com.example.health_diary.db.DbManager(contextVH)

        val tvTitle = itemView.findViewById<TextView>(R.id.TV_rc_task)
        val chBOX = itemView.findViewById<CheckBox>(R.id.checkBox)
        val context = contextVH



         fun getTimeset(): String {
            val time = Calendar.getInstance().time
            val formatter = SimpleDateFormat("dd.M.yyyy", Locale.getDefault())
            return formatter.format(time)

        }


        fun setData(item: ListExecution){

            DbManager.openDb()

            var taskTit = DbManager.readTaskTitle(item.taskEX_ID)
            var tasktitstring=  taskTit.toString()
            var titITOG = tasktitstring.substringAfter('[')
            titITOG = titITOG.substringBeforeLast(']')

                    try {
// заполнение выполнения привычки
                        var stdone = DbManager.readStaticDataForTask(item.taskEX_ID, getTimeset())
                        var iii = stdone.toString()
                        var itog = iii.substringAfter('[')
                        itog = itog.substringBeforeLast(']')
                        var itogId = Integer.parseInt(itog)

                        tvTitle.text = titITOG
                        if (itogId == 1) {
                            chBOX.isChecked = true
                            tvTitle.text = titITOG
                        }
                    } catch (e: Exception) {
                        tvTitle.text = titITOG
                    }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(inflater.inflate(R.layout.rc_itemfortask,parent,false),context)

    }

    override fun getItemCount(): Int {

        return listArray.size //передаем размер массива
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.setData(listArray.get(position))

    }

    fun updateAdapter(listItems :List<ListExecution>){
        listArray.clear()
        listArray.addAll(listItems)
        notifyDataSetChanged()
    }

    private fun getTime(): String {
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd.M.yyyy", Locale.getDefault())
        return formatter.format(time)

    }
    fun checkedTask(pos: Int, dbManager: DbManager ) {

       var exsist = dbManager.readStaticDataForTask(listArray[pos].taskEX_ID, getTime())
        if(exsist.toString() == "[]") {
            dbManager.insertToStatic(listArray[pos].taskEX_ID, getTime(), true)
        }


    }


}