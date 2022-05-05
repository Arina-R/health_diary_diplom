package com.example.health_diary.db

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.health_diary.CreateMenu
import com.example.health_diary.Create_H_W
import com.example.health_diary.R

class AdapterFood (listFood:ArrayList<ListFood>,contextF:Context ): RecyclerView.Adapter<AdapterFood.Holder>() {
    var listArray =listFood
    var context = contextF

    class Holder(itemView: View,contextVH: Context) : RecyclerView.ViewHolder(itemView) {

        val tvTitle = itemView.findViewById<TextView>(R.id.TV_rc_habit)
        val context = contextVH

        fun setData(item: ListFood){
            tvTitle.text = item.food_title
            itemView.setOnClickListener{
                val intent = Intent(context,CreateMenu::class.java).apply {

                    putExtra(IntentConstFood.I_title_food, item.food_title)
                    putExtra(IntentConstFood.I_id_food, item.food_ID)

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

    fun updateAdapter(listItems :List<ListFood>){
        listArray.clear()
        listArray.addAll(listItems)
        notifyDataSetChanged()

    }

    fun removeItem(pos: Int, dbManager: DbManager ){
        dbManager.removeTask(listArray[pos].food_ID)

        listArray.removeAt((pos))
        notifyItemRangeChanged(0,listArray.size)
        notifyItemRemoved(pos)

    }
}