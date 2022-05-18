package com.example.health_diary.db

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.health_diary.CreateMenu
import com.example.health_diary.R

class AdapterForTitleMenu (listFood:ArrayList<ListFood>,contextF: Context): RecyclerView.Adapter<AdapterForTitleMenu.Holder>() {
    var listArray = listFood
    var context = contextF

    class Holder(itemView: View, contextVH: Context) : RecyclerView.ViewHolder(itemView) {

        val tvTitle = itemView.findViewById<TextView>(R.id.TV_title_food)
        val context = contextVH

        fun setData(item: ListFood) {
            tvTitle.text = item.food_title


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(inflater.inflate(R.layout.rc_item, parent, false), context)

    }

    override fun getItemCount(): Int {

        return listArray.size //передаем размер массива
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setData(listArray.get(position))

    }

    fun updateAdapter(listItems: List<ListFood>) {
        listArray.clear()
        listArray.addAll(listItems)
        notifyDataSetChanged()

    }


}



