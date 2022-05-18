package com.example.health_diary.db

import android.provider.BaseColumns

object DbnameClass : BaseColumns {


    const val sqlite_sequence ="sqlite_sequence"
    const val column_seq = "seq"
    const val  column_name = "name"

//USER
    const val TABLE_USERS = "users"
    const val COLUMN_idUser = "userID"
    const val COLUMN_NAME = "name"

//TASK
    const val TABLE_TASK = "tasks"
    const val COLUMN_idTask = "idTask"
    const val COLUMN_IDUSER = "idUser"
    const val COLUMN_IDTYPE = "idType"
    const val COLUMN_TASKTITLE = "title"
    const val COLUMN_TIME = "time"

//TYPEOFTASK
    const val TABLE_TYPEOFTASK = "typeOFtask"
    const val COLUMN_idType = "idType"
    const val COLUMN_TYPETITLE = "title"

//STATISTIC
    const val TABLE_STATISTIC = "statistic"
    const val COLUMN_idStat = "idStat"
    const val COLUMN_IDTASK = "idTask"
    const val COLUMN_DATE = "date"
    const val COLUMN_DONE = "done"

//MENU
    const val TABLE_MENU = "menu"
    const val COLUMN_idMenu = "idMenu"
    const val COLUMN_DATEmenu = "date"
    const val COLUMN_IDFOOD = "idFood"
    const val COLUMN_QUANTITY = "quantity"
    const val COLUMN_TIMEFOOD   = "time"
    const val COLUMN_idUSERmenu ="idUser"

//FOOD
    const val TABLE_FOOD = "food"
    const val COLUMN_TITLE_MENU = "title"
    const val COLUMN_idFood = "idFood"
    const val COLUMN_CALORIES = "calories"

//EXECUTION
    const val TABLE_EXECUTIONDAY = "executionday"
    const val COLUMN_IDTASKex = "idTask"
    const val COLUMN_DAYOFWEEK = "dayOFweek"


    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "HealthDiary.db"

    //EXECUTION
    const val CREATE_TABLE_EXECUTION = "CREATE TABLE IF NOT EXISTS $TABLE_EXECUTIONDAY (" +
            "$COLUMN_IDTASKex INTEGER NOT NULL," +
            "$COLUMN_DAYOFWEEK TEXT NOT NULL," +
            "FOREIGN KEY($COLUMN_IDTASKex) REFERENCES $TABLE_TASK($COLUMN_idTask))"

    const val SQL_DELETE_EXECUTION = "DROP TABLE IF EXISTS $TABLE_EXECUTIONDAY"


//USER
    const val CREATE_TABLE_USERS =
        "CREATE TABLE IF NOT EXISTS $TABLE_USERS (" +
                "$COLUMN_idUser INTEGER NOT NULL," +
                "$COLUMN_NAME TEXT NOT NULL UNIQUE," +
                " PRIMARY KEY($COLUMN_idUser AUTOINCREMENT))"

    const val SQL_DELETE_USERS = "DROP TABLE IF EXISTS $TABLE_USERS"

//TASK
    const val CREATE_TABLE_TASKS = "CREATE TABLE IF NOT EXISTS $TABLE_TASK ($COLUMN_idTask INTEGER NOT NULL," +
                "    $COLUMN_IDUSER INTEGER NOT NULL," +
                "    $COLUMN_TASKTITLE TEXT NOT NULL," +
                "    $COLUMN_IDTYPE INTEGER NOT NULL," +
                "    $COLUMN_TIME TEXT NOT NULL," +
                "    FOREIGN KEY($COLUMN_IDUSER) REFERENCES $TABLE_USERS($COLUMN_idUser)," +
                "    PRIMARY KEY($COLUMN_idTask  AUTOINCREMENT))"

    const val SQL_DELETE_TASKS = "DROP TABLE IF EXISTS $TABLE_TASK"


//STATISTIC
const val CREATE_TABLE_STATISTIC = "CREATE TABLE IF NOT EXISTS $TABLE_STATISTIC ($COLUMN_idStat INTEGER NOT NULL," +
        "$COLUMN_IDTASK INTEGER NOT NULL," +
        "$COLUMN_DATE TEXT NOT NULL," +
        "$COLUMN_DONE BIT," +
        "PRIMARY KEY($COLUMN_idStat AUTOINCREMENT)," +
        "FOREIGN KEY($COLUMN_IDTASK) REFERENCES $TABLE_TASK($COLUMN_idTask))"

    const val SQL_DELETE_STATISTIC = "DROP TABLE IF EXISTS $TABLE_STATISTIC"



//MENU
    const val CREATE_TABLE_MENU = "CREATE TABLE IF NOT EXISTS $TABLE_MENU (" +
            " $COLUMN_idMenu INTEGER NOT NULL," +
            " $COLUMN_DATEmenu TEXT NOT NULL," +
        " $COLUMN_IDFOOD INTEGER NOT NULL," +
        " $COLUMN_QUANTITY INTEGER NOT NULL," +
        " $COLUMN_TIMEFOOD TEXT NOT NULL," +
            "$COLUMN_idUSERmenu INTEGER NOT NULL," +
            "FOREIGN KEY($COLUMN_idUSERmenu) REFERENCES $TABLE_USERS($COLUMN_idUser)" +
            " PRIMARY KEY($COLUMN_idMenu AUTOINCREMENT))"

    const val SQL_DELETE_MENU = "DROP TABLE IF EXISTS $TABLE_MENU"

//FOOD
    const val CREATE_TABLE_FOOD = "CREATE TABLE IF NOT EXISTS $TABLE_FOOD (" +
            "$COLUMN_idFood INTEGER NOT NULL," +
            "$COLUMN_TITLE_MENU TEXT NOT NULL UNIQUE," +
            "$COLUMN_CALORIES INTEGER NOT NULL," +
            "PRIMARY KEY($COLUMN_idFood AUTOINCREMENT))"

    const val SQL_DELETE_FOOD = "DROP TABLE IF EXISTS $TABLE_FOOD"


//TYPEOFTASK

    const val CREATE_TABLE_TYPEOFTASK = "CREATE TABLE IF NOT EXISTS $TABLE_TYPEOFTASK ("+
            "$COLUMN_idType INTEGER NOT NULL," +
            "$COLUMN_TYPETITLE TEXT NOT NULL UNIQUE," +
            "PRIMARY KEY($COLUMN_idType AUTOINCREMENT))"

    const val SQL_DELETE_TYPEOFTASK = "DROP TABLE IF EXISTS $TABLE_TYPEOFTASK"



}