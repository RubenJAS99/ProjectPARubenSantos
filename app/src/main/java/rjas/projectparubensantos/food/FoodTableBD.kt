package rjas.projectparubensantos.food

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import rjas.projectparubensantos.bd.BDTable

class FoodTableBD(db: SQLiteDatabase) : BDTable(db, NAME) {
    override fun create() {
        db.execSQL("CREATE TABLE $name (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$FOOD_NAME TEXT NOT NULL, $FOOD_TYPE TEXT NOT NULL, $FOOD_KCAL INTEGER," +
                "$FOOD_PROTEIN DOUBLE, $FOOD_FAT DOUBLE, $FOOD_HC DOUBLE)")
    }

    companion object {
        const val NAME = "food"
        const val FOOD_NAME = "name"
        const val FOOD_TYPE = "type"
        const val FOOD_KCAL = "kcal"
        const val FOOD_PROTEIN = "protein"
        const val FOOD_FAT = "fat"
        const val FOOD_HC = "carbohydrate"

        val ALL_COLUMNS = arrayOf(BaseColumns._ID, FOOD_NAME, FOOD_TYPE, FOOD_KCAL, FOOD_PROTEIN, FOOD_FAT, FOOD_HC)
    }
}