package com.example.health_diary.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class DbManager(context: Context) {
    val DbHelper = DbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb(){
        db = DbHelper.writableDatabase
    }

    //получение последнего id из таблицы
    fun readSequence(): ArrayList<String> {

        val dataList = ArrayList<String>()
        var dataText : String
        val selected = DbnameClass.column_name + " = \"tasks\""
        Log.d("Mylog", " $selected")
        val cursor = db?.query(DbnameClass.sqlite_sequence,null,selected,null,null,null,null)
        Log.d("Mylog", " $cursor")
        while (cursor?.moveToNext()!!){
             dataText = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.column_seq))
            dataList.add((dataText.toString()))
            Log.d("Mylog", "  data text  $dataText")
        }
        cursor.close()
        Log.d("Mylog", "  data text  $dataList")
        return dataList
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

    fun removeTask(id: Int){
        Log.d("Mylog","удаление task по id  $id")
        val selection = DbnameClass.COLUMN_idTask + " =$id"
        db?.delete(DbnameClass.TABLE_TASK,selection,null)
    }

    fun updatetask(title: String, time: String, type : Int, user: Int, id :Int){
        val selection = DbnameClass.COLUMN_idTask + " =$id"
        val values = ContentValues().apply {
            put(DbnameClass.COLUMN_TASKTITLE, title)
            put(DbnameClass.COLUMN_TIME, time)
            put(DbnameClass.COLUMN_IDTYPE, type)
            put(DbnameClass.COLUMN_IDUSER, user)
        }
        db?.update(DbnameClass.TABLE_TASK,values,selection,null)

    }

    fun readTasksData() : ArrayList<ListItemForHabits>{
        val dataList = ArrayList<ListItemForHabits>()


        val cursor = db?.query(DbnameClass.TABLE_TASK,null,null,null,null,null,null)

        while (cursor?.moveToNext()!!){
            val dataIdUser = cursor?.getInt(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_IDUSER))
            val dataTitile = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_TASKTITLE))
            val dataIdType = cursor?.getInt(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_IDTYPE))
            val dataTime = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_TIME))
            val dataTid = cursor?.getInt(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_idTask))
            val item = ListItemForHabits()
            item.useridTask = dataIdUser
            item.task_title = dataTitile
            item.typeidTask = dataIdType
            item.timeTask = dataTime
            item.task_ID = dataTid
            dataList.add(item)

        }
        cursor.close()

        return dataList
    }

    fun readHabitData() : ArrayList<ListItemForHabits>{
        val dataList = ArrayList<ListItemForHabits>()

        val selectHabit = DbnameClass.COLUMN_IDTYPE + " = 2"
        val cursor = db?.query(DbnameClass.TABLE_TASK,null,selectHabit,null,null,null,null)

        while (cursor?.moveToNext()!!){
            val dataIdUser = cursor?.getInt(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_IDUSER))
            val dataTitile = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_TASKTITLE))
            val dataIdType = cursor?.getInt(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_IDTYPE))
            val dataTime = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_TIME))
            val dataTid = cursor?.getInt(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_idTask))
            val item = ListItemForHabits()
            item.useridTask = dataIdUser
            item.task_title = dataTitile
            item.typeidTask = dataIdType
            item.timeTask = dataTime
            item.task_ID = dataTid
            dataList.add(item)

        }
        cursor.close()

        return dataList
    }

    fun readWorkoutData() : ArrayList<ListItemForHabits>{
        val dataList = ArrayList<ListItemForHabits>()

        val selectHabit = DbnameClass.COLUMN_IDTYPE + " = 1"
        val cursor = db?.query(DbnameClass.TABLE_TASK,null,selectHabit,null,null,null,null)

        while (cursor?.moveToNext()!!){
            val dataIdUser = cursor?.getInt(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_IDUSER))
            val dataTitile = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_TASKTITLE))
            val dataIdType = cursor?.getInt(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_IDTYPE))
            val dataTime = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_TIME))
            val dataTid = cursor?.getInt(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_idTask))
            val item = ListItemForHabits()
            item.useridTask = dataIdUser
            item.task_title = dataTitile
            item.typeidTask = dataIdType
            item.timeTask = dataTime
            item.task_ID = dataTid
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



    // работа c меню
    fun insertToMenu( idUser : Int,idFood : Int, quantity:Int,time: String,  date: String){
        val values = ContentValues().apply {
            put(DbnameClass.COLUMN_DATEmenu, date)
            put(DbnameClass.COLUMN_IDFOOD, idFood)
            put(DbnameClass.COLUMN_QUANTITY, quantity)
            put(DbnameClass.COLUMN_TIMEFOOD, time)
            put(DbnameClass.COLUMN_idUSERmenu, idUser)
        }

        db?.insert(DbnameClass.TABLE_MENU,null,values)

    }

    fun searchFood(name: String): ArrayList<String> {
        val dataList = ArrayList<String>()
        var dataText : String
        val selected = DbnameClass.COLUMN_TITLE_MENU + " = \"$name\""

        val cursor = db?.query(DbnameClass.TABLE_FOOD,null,selected,null,null,null,null)

        while (cursor?.moveToNext()!!){
            dataText = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.column_seq))
            dataList.add((dataText.toString()))

        }
        cursor.close()

        return dataList
    }


    fun readMenuBreakfastData() : ArrayList<ListMenu>{
        val dataList = ArrayList<ListMenu>()

        val selected = DbnameClass.COLUMN_TIMEFOOD + " = \"Завтрак\""
        val cursor = db?.query(DbnameClass.TABLE_MENU,null,selected,null,null,null,null)

        while (cursor?.moveToNext()!!){
            val dataMenuDate = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_DATEmenu))
            val dataMenuID = cursor?.getInt(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_idMenu))
            val datafoodID = cursor?.getInt(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_IDFOOD))
            val dataQuantity = cursor?.getInt(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_QUANTITY))
            val datatime = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_TIMEFOOD))
            val item = ListMenu()
            item.menu_ID = dataMenuID
            item.menu_quant = dataQuantity
            item.food_ID = datafoodID
            item.menu_date = dataMenuDate
            item.menu_time = datatime

            dataList.add(item)

        }
        cursor.close()

        return dataList
    }

    fun readMenulunchData() : ArrayList<ListMenu>{
        val dataList = ArrayList<ListMenu>()

        val selected = DbnameClass.COLUMN_TIMEFOOD + " = \"Обед\""
        val cursor = db?.query(DbnameClass.TABLE_MENU,null,selected,null,null,null,null)

        while (cursor?.moveToNext()!!){
            val dataMenuDate = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_DATEmenu))
            val dataMenuID = cursor?.getInt(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_idMenu))
            val datafoodID = cursor?.getInt(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_IDFOOD))
            val dataQuantity = cursor?.getInt(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_QUANTITY))
            val datatime = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_TIMEFOOD))
            val item = ListMenu()
            item.menu_ID = dataMenuID
            item.menu_quant = dataQuantity
            item.food_ID = datafoodID
            item.menu_date = dataMenuDate
            item.menu_time = datatime

            dataList.add(item)

        }
        cursor.close()

        return dataList
    }

    fun readMenuDinnerData() : ArrayList<ListMenu>{
        val dataList = ArrayList<ListMenu>()

        val selected = DbnameClass.COLUMN_TIMEFOOD + " = \"Ужин\""
        val cursor = db?.query(DbnameClass.TABLE_MENU,null,selected,null,null,null,null)

        while (cursor?.moveToNext()!!){
            val dataMenuDate = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_DATEmenu))
            val dataMenuID = cursor?.getInt(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_idMenu))
            val datafoodID = cursor?.getInt(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_IDFOOD))
            val dataQuantity = cursor?.getInt(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_QUANTITY))
            val datatime = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_TIMEFOOD))
            val item = ListMenu()
            item.menu_ID = dataMenuID
            item.menu_quant = dataQuantity
            item.food_ID = datafoodID
            item.menu_date = dataMenuDate
            item.menu_time = datatime

            dataList.add(item)

        }
        cursor.close()

        return dataList
    }

    fun readMenuSnackData() : ArrayList<ListMenu>{
        val dataList = ArrayList<ListMenu>()

        val selected = DbnameClass.COLUMN_TIMEFOOD + " = \"Перекус\""
        val cursor = db?.query(DbnameClass.TABLE_MENU,null,selected,null,null,null,null)

        while (cursor?.moveToNext()!!){
            val dataMenuDate = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_DATEmenu))
            val dataMenuID = cursor?.getInt(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_idMenu))
            val datafoodID = cursor?.getInt(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_IDFOOD))
            val dataQuantity = cursor?.getInt(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_QUANTITY))
            val datatime = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_TIMEFOOD))
            val item = ListMenu()
            item.menu_ID = dataMenuID
            item.menu_quant = dataQuantity
            item.food_ID = datafoodID
            item.menu_date = dataMenuDate
            item.menu_time = datatime

            dataList.add(item)

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

    fun readFoodData() : ArrayList<ListFood>{
        val dataList = ArrayList<ListFood>()

        val cursor = db?.query(DbnameClass.TABLE_FOOD,null,null,null,null,null,null)

        while (cursor?.moveToNext()!!){
            val dataTitle = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_TITLE_MENU))
            val dataidfood = cursor?.getInt(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_idFood))
            val item = ListFood()
            item.food_ID = dataidfood
            item.food_title = dataTitle
            dataList.add(item)

        }
        cursor.close()

        return dataList
    }


    fun readFoodTitle(id : Int): Cursor? {

        var selected = DbnameClass.COLUMN_idFood  + " =$id"
        val cursor = db?.query(DbnameClass.TABLE_FOOD,null,selected,null,null,null,null)

       return  cursor


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

    fun removeExecution(id: Int){
        Log.d("Mylog","удаление  ex по id  $id")
        val selection = DbnameClass.COLUMN_IDTASKex + " =$id"
        db?.delete(DbnameClass.TABLE_EXECUTIONDAY,selection,null)
    }

    fun closeDb(){
        DbHelper.close()
    }

}