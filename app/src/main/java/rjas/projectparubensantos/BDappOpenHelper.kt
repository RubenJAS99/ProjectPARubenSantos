package rjas.projectparubensantos

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BDappOpenHelper(context: Context?) : SQLiteOpenHelper(context, NAME, null, VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        requireNotNull(db)

        UserTableBD(db).create()
        FoodTableBD(db).create()
        ProgressTableBD(db).create()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
    companion object {
        const val NAME = "App.db"
        private const val VERSION = 1
    }

}
