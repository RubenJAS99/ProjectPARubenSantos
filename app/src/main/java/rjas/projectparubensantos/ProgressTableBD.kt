package rjas.projectparubensantos

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class ProgressTableBD(db: SQLiteDatabase) : BDTable(db, NAME) {
    override fun create() {
        db.execSQL("CREATE TABLE $name (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$PROGRESS_DATE DATE NOT NULL, $LAST_WEIGHT DOUBLE NOT NULL, $CURRENT_WEIGHT DOUBLE NOT NULL," +
                "$USER_PERIOD TEXT NOT NULL)")
    }

    companion object {
        const val NAME = "progress"
        const val PROGRESS_DATE = "date"
        const val LAST_WEIGHT = "lastWeight"
        const val CURRENT_WEIGHT = "currentWeight"
        const val USER_PERIOD = "period"

        val ALL_COLUMNS = arrayOf(BaseColumns._ID, PROGRESS_DATE, LAST_WEIGHT, CURRENT_WEIGHT, USER_PERIOD)
    }
}