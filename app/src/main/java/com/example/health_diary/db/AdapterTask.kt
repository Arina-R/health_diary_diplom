package com.example.health_diary.db

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.health_diary.Create_H_W
import com.example.health_diary.R

class AdapterTask(listTask:ArrayList<ListItemForHabits>,contextT: Context): RecyclerView.Adapter<AdapterTask.Holder>() {

    var listArray =listTask
    var context = contextT

    class Holder(itemView: View, contextVH: Context) : RecyclerView.ViewHolder(itemView) {

        val tvTitle = itemView.findViewById<TextView>(R.id.TV_rc_task)
        val context = contextVH

        fun setData(item: ListItemForHabits){
            tvTitle.text = item.task_title
            itemView.setOnClickListener{

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

    fun updateAdapter(listItems :List<ListItemForHabits>){
        listArray.clear()
        listArray.addAll(listItems)
        notifyDataSetChanged()


    }



}