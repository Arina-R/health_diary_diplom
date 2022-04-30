package com.example.health_diary.db

import android.app.LauncherActivity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.util.Log.DEBUG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.health_diary.Create_H_W
import com.example.health_diary.R

class AdapterHabit(listHabit:ArrayList<ListItemForHabits>,contextH:Context ): RecyclerView.Adapter<AdapterHabit.Holder>() {

    var listArray =listHabit
    var context = contextH

    class Holder(itemView: View,contextVH: Context) : RecyclerView.ViewHolder(itemView) {

        val tvTitle = itemView.findViewById<TextView>(R.id.TV_rc_habit)
        val context = contextVH

        fun setData(item: ListItemForHabits){
            tvTitle.text = item.title
            itemView.setOnClickListener{
                val intent = Intent(context,Create_H_W::class.java).apply {

                    putExtra(IntentConstHab.I_title,item.title)
                    putExtra(IntentConstHab.I_time,item.time)
                    putExtra(IntentConstHab.I_typeid,item.typeid)
                    putExtra(IntentConstHab.I_userid,item.userid)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(inflater.inflate(R.layout.rc_item,parent,false),context)
        Log.d("Mylog","createview")
    }

    override fun getItemCount(): Int {
        Log.d("Mylog","getite,")
        return listArray.size //передаем размер массива
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
       holder.setData(listArray.get(position))
        Log.d("Mylog","bind")
    }

    fun updateAdapter(listItems :List<ListItemForHabits>){
        listArray.clear()
        listArray.addAll(listItems)
        notifyDataSetChanged()
        Log.d("Mylog","update")
    }

}