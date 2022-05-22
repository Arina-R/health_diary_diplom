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
        val cursor = db?.query(DbnameClass.sqlite_sequence,null,selected,null,null,null,null)
        while (cursor?.moveToNext()!!){
             dataText = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.column_seq))
            dataList.add((dataText.toString()))
        }
        cursor.close()
        return dataList
    }

//работа с таблицей пользователей
    fun insertToUsers(name: String){
        val values = ContentValues().apply {
            put(DbnameClass.COLUMN_NAME, name)
        }

        db?.insert(DbnameClass.TABLE_USERS,null,values)

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

    fun readTaskTitle(id : Int): ArrayList<String>{
        val dataList = ArrayList<String>()

        var selected = DbnameClass.COLUMN_idTask  + " =$id"
        val cursor = db?.query(DbnameClass.TABLE_TASK,null,selected,null,null,null,null)

        while (cursor?.moveToNext()!!){
            val dataText = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_TASKTITLE))
            dataList.add((dataText.toString()))
        }
        cursor.close()
        return dataList

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



    // работа со статистикой
    fun insertToStatic(idtask: Int, date : String, done: Boolean){
        val values = ContentValues().apply {
            put(DbnameClass.COLUMN_IDTASK, idtask)
            put(DbnameClass.COLUMN_DATE, date)
            put(DbnameClass.COLUMN_DONE, done)

        }

        db?.insert(DbnameClass.TABLE_STATISTIC,null,values)

    }

    fun readStaticData(curDate:String) : Int {
        val dataList = ArrayList<String>()

        val selected = DbnameClass.COLUMN_DATE + " = \"$curDate\""
        val cursor = db?.query(DbnameClass.TABLE_STATISTIC,null,selected,null,null,null,null)
        while (cursor?.moveToNext()!!){
            val dataText = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_DATE))
            dataList.add((dataText.toString()))
        }
        cursor.close()
        return dataList.size
    }

    fun readStaticDataForTask(idtask: Int,date:String ) : ArrayList<String> {
        val dataList = ArrayList<String>()

        val selected = DbnameClass.COLUMN_IDTASK + " = $idtask" +" AND " + DbnameClass.COLUMN_DATE + " = \"$date\""
        val cursor = db?.query(DbnameClass.TABLE_STATISTIC,null,selected,null,null,null,null)
        while (cursor?.moveToNext()!!){
            val dataText = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_DONE))
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

    fun readMenuBreakfastData(date: String) : ArrayList<ListMenu>{
        val dataList = ArrayList<ListMenu>()

        val selected = DbnameClass.COLUMN_TIMEFOOD + " = \"Завтрак\"" +" AND " + DbnameClass.COLUMN_DATEmenu + " = \"$date\""
        Log.d("Mylog","hfhfh ${selected.toString()}")
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

    fun readMenulunchData(date: String) : ArrayList<ListMenu>{
        val dataList = ArrayList<ListMenu>()

        val selected = DbnameClass.COLUMN_TIMEFOOD + " = \"Обед\"" +" AND " + DbnameClass.COLUMN_DATEmenu + " = \"$date\""
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

    fun readMenuDinnerData(date: String) : ArrayList<ListMenu>{
        val dataList = ArrayList<ListMenu>()

        val selected = DbnameClass.COLUMN_TIMEFOOD + " = \"Ужин\"" +" AND " + DbnameClass.COLUMN_DATEmenu + " = \"$date\""
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

    fun readMenuSnackData(date: String) : ArrayList<ListMenu>{
        val dataList = ArrayList<ListMenu>()

        val selected = DbnameClass.COLUMN_TIMEFOOD + " = \"Перекус\""+" AND " + DbnameClass.COLUMN_DATEmenu + " = \"$date\""
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


    fun readFoodTitle(id : Int): ArrayList<String>{
        val dataList = ArrayList<String>()

        var selected = DbnameClass.COLUMN_idFood  + " =$id"
        val cursor = db?.query(DbnameClass.TABLE_FOOD,null,selected,null,null,null,null)

            while (cursor?.moveToNext()!!){
                val dataText = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_TITLE_MENU))
                dataList.add((dataText.toString()))
            }
            cursor.close()
            return dataList

    }

    fun readFoodCalories(id : Int): ArrayList<Int>{
        val dataList = ArrayList<Int>()

        var selected = DbnameClass.COLUMN_idFood  + " =$id"
        val cursor = db?.query(DbnameClass.TABLE_FOOD,null,selected,null,null,null,null)

        while (cursor?.moveToNext()!!){
            val dataText = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_CALORIES))
            dataList.add((dataText.toInt()))
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

    fun readExecutionData(week :String) : ArrayList<ListExecution>{
        val dataList = ArrayList<ListExecution>()

        var selected = DbnameClass.COLUMN_DAYOFWEEK + "= \"$week\""
        val cursor = db?.query(DbnameClass.TABLE_EXECUTIONDAY,null,selected,null,null,null,null)

        while (cursor?.moveToNext()!!){
            val dataIDtask = cursor?.getInt(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_IDTASKex))
            val dataEXday = cursor?.getString(cursor.getColumnIndexOrThrow(DbnameClass.COLUMN_DAYOFWEEK))
            val item = ListExecution()
            item.taskEX_ID = dataIDtask
            item.ex_date = dataEXday
            dataList.add(item)

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