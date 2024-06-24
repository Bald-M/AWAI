package com.example.awai

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DatabaseHelper(context: Context) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table record(id integer primary key autoincrement,name text,address text,phone text,email text,relations text)")
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }


    fun insertRecord(name: String, address: String, phone: String, email: String, relations: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("address", address)
        contentValues.put("phone", phone)
        contentValues.put("email", email)
        contentValues.put("relations", relations)
        db.insert("record", null, contentValues)
    }
    fun updateRecord(id: String,name: String, address: String, phone: String, email: String, relations: String):
            Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("address", address)
        contentValues.put("phone", phone)
        contentValues.put("email", email)
        contentValues.put("relations", relations)
        db.update("record", contentValues, "id = ?", arrayOf(id))
        return true
    }

    fun deleteRecord(id : String){
        val db = this.writableDatabase
        db.delete("record","id = ?", arrayOf(id))
    }

    fun checkPoliceRecord(): Cursor {
        val db = this.writableDatabase
        val res = db.rawQuery("SELECT * FROM record WHERE name = ?", arrayOf("Police"))
        return res
    }


    val allData : Cursor
        get() {
            val db = this.writableDatabase
            val res = db.rawQuery("SELECT * FROM record", null)
            return res
        }

    companion object {
        val DATABASE_NAME = "awai.db"
    }
}