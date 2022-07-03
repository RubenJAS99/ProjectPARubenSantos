package rjas.projectparubensantos.food

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Food(
    var foodName : String,
    var foodTypeId: Long,
    var kcal: Int,
    var protein: Double,
    var fat: Double,
    var carbohydrate: Double,
    var id: Long = -1
) {
    fun toContentValues(): ContentValues {
        val values = ContentValues()

        values.put(FoodTableBD.FOOD_NAME, foodName)
        values.put(FoodTableBD.FOOD_TYPE_ID, foodTypeId)
        values.put(FoodTableBD.FOOD_KCAL, kcal)
        values.put(FoodTableBD.FOOD_PROTEIN, protein)
        values.put(FoodTableBD.FOOD_FAT, fat)
        values.put(FoodTableBD.FOOD_HC, carbohydrate)

        return values
    }

    companion object {
        fun fromCursor(cursor: Cursor): Food {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posFoodName = cursor.getColumnIndex(FoodTableBD.FOOD_NAME)
            val posFoodType = cursor.getColumnIndex(FoodTableBD.FOOD_TYPE_ID)
            val posFoodKcal = cursor.getColumnIndex(FoodTableBD.FOOD_KCAL)
            val posFoodProtein = cursor.getColumnIndex(FoodTableBD.FOOD_PROTEIN)
            val posFoodFat = cursor.getColumnIndex(FoodTableBD.FOOD_FAT)
            val posFoodHC = cursor.getColumnIndex(FoodTableBD.FOOD_HC)


            val id = cursor.getLong(posId)
            val foodName = cursor.getString(posFoodName)
            val foodTypeId= cursor.getLong(posFoodType)
            val kcal = cursor.getInt(posFoodKcal)
            val protein = cursor.getDouble(posFoodProtein)
            val fat = cursor.getDouble(posFoodFat)
            val carbohydrate = cursor.getDouble(posFoodHC)

            return Food(foodName, foodTypeId, kcal, protein, fat, carbohydrate, id)
        }
    }
}
