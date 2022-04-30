package com.example.health_diary.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class DbManager(context: Context) {
    val DbHelper = DbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb(){
        db = DbHelper.writableDatabase
    }


//работа с таблицей пользователей
    fun insertToUsers(name: String){
        val values = ContentValues().apply {
            put(DbnameClass.COLUMN_NAME, name)
        }

        db?.insert(DbnameClass.TABLE_USERS,null,values)

    }

    fun readUsersData() : ArrayList<String>{
        val dataList = ArrayList<String>()

        val cursor = db?.query(DbnameClass.TABLE_USERS,null,null,null,null,null,null)

            while (cursor?.moveToNext()!!){
                val dataText = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_NAME))
                dataList.add((dataText.toString()))

            }
        cursor.close()

        return dataList
    }


// работа с заданиями
    fun insertToUTask(title: String, time: String, type : Int, user: Int){
        val values = ContentValues().apply {
            put(DbnameClass.COLUMN_TASKTITLE, title)
            put(DbnameClass.COLUMN_TIME, time)
            put(DbnameClass.COLUMN_IDTYPE, type)
            put(DbnameClass.COLUMN_IDUSER, user)
        }

        db?.insert(DbnameClass.TABLE_TASK,null,values)

    }

    fun readTaskData() : ArrayList<ListItemForHabits>{
        val dataList = ArrayList<ListItemForHabits>()

        val cursor = db?.query(DbnameClass.TABLE_TASK,null,null,null,null,null,null)

        while (cursor?.moveToNext()!!){
            val dataIdUser = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_IDUSER))
            val dataTitile = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_TASKTITLE))
            val dataIdType = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_IDTYPE))
            val dataTime = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_TIME))
            val item = ListItemForHabits()
            item.userid = dataIdUser.toInt()
            item.title = dataTitile
            item.typeid = dataIdType.toInt()
            item.time = dataTime
            dataList.add(item)

        }
        cursor.close()

        return dataList
    }



    // работа с типами заданий
    fun insertToType(title: String){
        val values = ContentValues().apply {
            put(DbnameClass.COLUMN_TYPETITLE, title)
        }

        db?.insert(DbnameClass.TABLE_TYPEOFTASK,null,values)

    }

    fun readTypeData() : ArrayList<String>{
        val dataList = ArrayList<String>()

        val cursor = db?.query(DbnameClass.TABLE_TYPEOFTASK,null,null,null,null,null,null)

        while (cursor?.moveToNext()!!){
            val dataText = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_TYPETITLE))
            dataList.add((dataText.toString()))

        }
        cursor.close()

        return dataList
    }


    // работа со статистикой
    fun insertToStatic(idtask: Int, date : String, done: Boolean){
        val values = ContentValues().apply {
            put(DbnameClass.COLUMN_IDTASK, idtask)
            put(DbnameClass.COLUMN_DATE, date)
            put(DbnameClass.COLUMN_DONE, done)

        }

        db?.insert(DbnameClass.TABLE_STATISTIC,null,values)

    }

    fun readStaticData() : ArrayList<String>{
        val dataList = ArrayList<String>()

        val cursor = db?.query(DbnameClass.TABLE_STATISTIC,null,null,null,null,null,null)

        while (cursor?.moveToNext()!!){
            val dataText = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_DATE))
            dataList.add((dataText.toString()))

        }
        cursor.close()

        return dataList
    }


    // работа о связью меню и еды
    fun insertToMenuFood(idMenu: Int, idFood: Int, quantity : Int, time: String){
        val values = ContentValues().apply {
            put(DbnameClass.COLUMN_IDMENU, idMenu)
            put(DbnameClass.COLUMN_IDFOOD, idFood)
            put(DbnameClass.COLUMN_QUANTITY, quantity)
            put(DbnameClass.COLUMN_TIMEFOOD, time)

        }

        db?.insert(DbnameClass.TABLE_MENUFOOD,null,values)

    }

    fun readMenuFoodData() : ArrayList<String>{
        val dataList = ArrayList<String>()

        val cursor = db?.query(DbnameClass.TABLE_MENUFOOD,null,null,null,null,null,null)

        while (cursor?.moveToNext()!!){
            val dataText = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_QUANTITY))
            dataList.add((dataText.toString()))

        }
        cursor.close()

        return dataList
    }

    // работа c меню
    fun insertToMenu( idUser : Int, date: String){
        val values = ContentValues().apply {
            put(DbnameClass.COLUMN_DATEmenu, date)
            put(DbnameClass.COLUMN_idUSERmenu, idUser)
        }

        db?.insert(DbnameClass.TABLE_MENU,null,values)

    }

    fun readMenuData() : ArrayList<String>{
        val dataList = ArrayList<String>()

        val cursor = db?.query(DbnameClass.TABLE_MENU,null,null,null,null,null,null)

        while (cursor?.moveToNext()!!){
            val dataText = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_DATEmenu))
            dataList.add((dataText.toString()))

        }
        cursor.close()

        return dataList
    }

    // работа с едой
    fun insertToFood(title: String, calories: Int){
        val values = ContentValues().apply {
            put(DbnameClass.COLUMN_TITLE_MENU, title)
            put(DbnameClass.COLUMN_CALORIES, calories)
        }

        db?.insert(DbnameClass.TABLE_FOOD,null,values)

    }

    fun readFoodData() : ArrayList<String>{
        val dataList = ArrayList<String>()

        val cursor = db?.query(DbnameClass.TABLE_FOOD,null,null,null,null,null,null)

        while (cursor?.moveToNext()!!){
            val dataText = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_TITLE_MENU))
            dataList.add((dataText.toString()))

        }
        cursor.close()

        return dataList
    }

    // работа с днями недели
    fun insertToExecution(id: Int,day: String){
        val values = ContentValues().apply {
            put(DbnameClass.COLUMN_IDTASKex, id)
            put(DbnameClass.COLUMN_DAYOFWEEK, day)
        }

        db?.insert(DbnameClass.TABLE_EXECUTIONDAY,null,values)

    }

    fun readExecutionData() : ArrayList<String>{
        val dataList = ArrayList<String>()

        val cursor = db?.query(DbnameClass.TABLE_EXECUTIONDAY,null,null,null,null,null,null)

        while (cursor?.moveToNext()!!){
            val dataText = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_DAYOFWEEK))
            dataList.add((dataText.toString()))

        }
        cursor.close()

        return dataList
    }

    fun closeDb(){
        DbHelper.close()
    }

}