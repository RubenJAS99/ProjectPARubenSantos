package rjas.projectparubensantos

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class UserTableBD(db: SQLiteDatabase) : BDTable(db, NAME) {
    override fun create() {
        db.execSQL("CREATE TABLE $name (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$USER_NAME TEXT NOT NULL, $USER_WEIGHT DOUBLE NOT NULL, $USER_HEIGHT INTEGER NOT NULL," +
                "$USER_ACTIVITY_LEVEL DOUBLE NOT NULL, $USER_PERIOD TEXT NOT NULL)")
    }

    companion object {
        const val NAME = "user"
        const val USER_NAME = "name"
        const val USER_WEIGHT = "weight"
        const val USER_HEIGHT = "height"
        const val USER_ACTIVITY_LEVEL = "activity_level"
        const val USER_PERIOD = "period"

        val ALL_COLUMNS = arrayOf(BaseColumns._ID, USER_NAME, USER_WEIGHT, USER_HEIGHT, USER_ACTIVITY_LEVEL, USER_PERIOD)
    }
}