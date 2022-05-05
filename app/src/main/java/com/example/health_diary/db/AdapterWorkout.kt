package com.example.health_diary.db

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.health_diary.Create_H_W
import com.example.health_diary.R

class AdapterWorkout(listWorkout:ArrayList<ListItemForHabits>,contextW:Context ): RecyclerView.Adapter<AdapterWorkout.Holder>()  {

    var listArray =listWorkout
    var context = contextW

    class Holder(itemView: View,contextVH: Context) : RecyclerView.ViewHolder(itemView) {

        val tvTitle = itemView.findViewById<TextView>(R.id.TV_rc_habit)
        val context = contextVH

        fun setData(item: ListItemForHabits){
            tvTitle.text = item.task_title
            itemView.setOnClickListener{
                val intent = Intent(context,Create_H_W::class.java).apply {

                    putExtra(IntentConstTask.I_title,item.task_title)
                    putExtra(IntentConstTask.I_time,item.timeTask)
                    putExtra(IntentConstTask.I_typeid,item.typeidTask)
                    putExtra(IntentConstTask.I_userid,item.useridTask)
                    putExtra(IntentConstTask.I_id_task, item.task_ID)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(inflater.inflate(R.layout.rc_item,parent,false),context)

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

    fun removeItem(pos: Int, dbManager: DbManager ){
        dbManager.removeTask(listArray[pos].task_ID)

        listArray.removeAt((pos))
        notifyItemRangeChanged(0,listArray.size)
        notifyItemRemoved(pos)

    }



}