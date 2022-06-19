package rjas.projectparubensantos

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class FoodTableBD(db: SQLiteDatabase) : BDTable(db, NAME) {
    override fun create() {
        db.execSQL("CREATE TABLE $name (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$FOOD_NAME TEXT NOT NULL, $FOOD_TYPE TEXT NOT NULL, $FOOD_KCAL INTEGER NOT NULL," +
                "$FOOD_PROTEIN DOUBLE NOT NULL, $FOOD_FAT DOUBLE NOT NULL, $FOOD_HC DOUBLE NOT NULL)")
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