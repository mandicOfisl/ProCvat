package hr.algebra.lmandic.procvat.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private const val DB_NAME = "procvat.db"
private const val DB_VERSION = 1
private const val TABLE_NAME = "procvat"
private const val DROP_TABLE = "drop table $TABLE_NAME"

class ProcvatSqlHelper(context: Context?)
    : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION), ProcvatRepo {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    override fun delete(selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Not yet implemented")
    }

    override fun insert(values: ContentValues?): Long {
        TODO("Not yet implemented")
    }

    override fun query(
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        TODO("Not yet implemented")
    }

    override fun update(
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("Not yet implemented")
    }

}