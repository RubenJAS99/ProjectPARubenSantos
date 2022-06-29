package rjas.projectparubensantos.period

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import rjas.projectparubensantos.bd.BDTable

class PeriodTableBD(db: SQLiteDatabase) : BDTable(db, NAME) {
    override fun create() {
        db.execSQL("CREATE TABLE $name (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$USER_PERIOD TEXT NOT NULL)")
    }

    companion object {
        const val NAME = "period"
        const val USER_PERIOD = "name"

        val ALL_COLUMNS = arrayOf(BaseColumns._ID, USER_PERIOD)
    }
}