package com.example.health_diary.db

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.health_diary.Nav_Pitanie
import com.example.health_diary.R
import android.content.Context as Context1

class AdapterMenu (listMenu:ArrayList<ListMenu>, contextM: Context1): RecyclerView.Adapter<AdapterMenu.Holder>() {
    var listArray =listMenu
          //  var  listArrayFood = listFood
      var context = contextM
        val DbManager = com.example.health_diary.db.DbManager(contextM)




    class Holder(itemView: View, contextVM: Context1) : RecyclerView.ViewHolder(itemView) {
        val DbManager = com.example.health_diary.db.DbManager(contextVM)

            val tvTitle = itemView.findViewById<TextView>(R.id.TV_title_food)

            val tvQua = itemView.findViewById<TextView>(R.id.TV_menu_qua)
            val context = contextVM

            fun setData( itemMenu: ListMenu){
                DbManager.openDb()
                var sets =  DbManager.readFoodTitle(itemMenu.food_ID)

                var iii=  sets.toString()
                var itog = iii.substringAfter('[')
                itog = itog.substringBeforeLast(']')
               tvTitle.text = itog


                var tvcal = DbManager.readFoodCalories(itemMenu.food_ID)

                var calor=  tvcal.toString()
                var itogcalor = calor.substringAfter('[')
                itogcalor = itogcalor.substringBeforeLast(']')
                var itogCAL = Integer.parseInt(itogcalor)
                var sumcal = itemMenu.menu_quant.toDouble() * itogCAL / 100
                tvQua.text= "$sumcal калл.(${itemMenu.menu_quant.toInt()}г.)"
            }

    }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val inflater = LayoutInflater.from(parent.context)
            return Holder(inflater.inflate(R.layout.rc_foodtime,parent,false),context)

        }

        override fun getItemCount(): Int {

            return listArray.size //передаем размер массива
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.setData(listArray.get(position))


        }

        fun updateAdapter(listItems :List<ListMenu>){
            listArray.clear()
            listArray.addAll(listItems)
            notifyDataSetChanged()

        }

}