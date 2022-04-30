package com.example.health_diary.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DbHelper(context: Context) : SQLiteOpenHelper(context, DbnameClass.DATABASE_NAME, null, DbnameClass.DATABASE_VERSION)
{
    override fun onCreate(db: SQLiteDatabase) {
        db?.execSQL(DbnameClass.CREATE_TABLE_USERS)
        db?.execSQL(DbnameClass.CREATE_TABLE_FOOD)
        db?.execSQL(DbnameClass.CREATE_TABLE_MENU)
        db?.execSQL(DbnameClass.CREATE_TABLE_MENUFOOD)
        db?.execSQL(DbnameClass.CREATE_TABLE_TASKS)
        db?.execSQL(DbnameClass.CREATE_TABLE_TYPEOFTASK)
        db?.execSQL(DbnameClass.CREATE_TABLE_STATISTIC)
        db?.execSQL(DbnameClass.CREATE_TABLE_EXECUTION)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db?.execSQL(DbnameClass.SQL_DELETE_MENUFOOD)
        db?.execSQL(DbnameClass.SQL_DELETE_STATISTIC)
        db?.execSQL(DbnameClass.SQL_DELETE_TASKS)
        db?.execSQL(DbnameClass.SQL_DELETE_TYPEOFTASK)
        db?.execSQL(DbnameClass.SQL_DELETE_EXECUTION)
        db?.execSQL(DbnameClass.SQL_DELETE_FOOD)
        db?.execSQL(DbnameClass.SQL_DELETE_MENU)
        db?.execSQL(DbnameClass.SQL_DELETE_USERS)
        onCreate(db)}


}