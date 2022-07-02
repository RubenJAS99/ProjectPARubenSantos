package rjas.projectparubensantos.food

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import rjas.projectparubensantos.bd.BDTable

class FoodTypeTableBD(db: SQLiteDatabase) : BDTable(db, NAME) {
    override fun create() {
        db.execSQL("CREATE TABLE $name (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$FOOD_TYPE TEXT NOT NULL)")
    }

    companion object {
        const val NAME = "foodtype"
        const val FOOD_TYPE = "name"

        val ALL_COLUMNS = arrayOf(BaseColumns._ID, FOOD_TYPE)
    }
}