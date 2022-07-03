package rjas.projectparubensantos.food

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.provider.BaseColumns
import rjas.projectparubensantos.bd.BDTable

class FoodTableBD(db: SQLiteDatabase) : BDTable(db, NAME) {
    override fun create() {
        db.execSQL("CREATE TABLE $name (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$FOOD_NAME TEXT NOT NULL, $FOOD_KCAL INTEGER," +
                "$FOOD_PROTEIN DOUBLE, $FOOD_FAT DOUBLE, $FOOD_HC DOUBLE," +
                "$FOOD_TYPE_ID INTEGER NOT NULL, FOREIGN KEY ($FOOD_TYPE_ID) " +
                "REFERENCES ${FoodTypeTableBD.NAME}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }

    override fun query(
        columns: Array<String>,
        selection: String?,
        selectionArgs: Array<String>?,
        groupBy: String?,
        having: String?,
        orderBy: String?
    ): Cursor {
        val queryBuilder = SQLiteQueryBuilder()
        queryBuilder.tables = "$NAME INNER JOIN ${FoodTypeTableBD.NAME} ON ${FoodTypeTableBD.NAME}.${BaseColumns._ID} = $FOOD_TYPE_ID"

        return queryBuilder.query(db, columns, selection, selectionArgs, groupBy, having, orderBy)
    }

    companion object {
        const val NAME = "food"

        const val ID_VALUE = "$NAME.${BaseColumns._ID}"
        const val FOOD_NAME = "foodName"
        const val FOOD_KCAL = "kcal"
        const val FOOD_PROTEIN = "protein"
        const val FOOD_FAT = "fat"
        const val FOOD_HC = "carbohydrate"
        const val FOOD_TYPE_ID = "foodTypeId"

        val ALL_COLUMNS = arrayOf(ID_VALUE, FOOD_NAME, FOOD_KCAL, FOOD_PROTEIN, FOOD_FAT, FOOD_HC, FOOD_TYPE_ID)
    }
}