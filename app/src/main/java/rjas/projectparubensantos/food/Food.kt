package rjas.projectparubensantos.food

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Food(
    var foodName : String,
    var foodTypeId: Type,
    var kcal: Int,
    var protein: Double,
    var fat: Double,
    var carbohydrate: Double,
    var id: Long = -1
) {
    fun toContentValues(): ContentValues {
        val values = ContentValues()

        values.put(FoodTableBD.FOOD_NAME, foodName)
        values.put(FoodTableBD.FOOD_TYPE_ID, foodTypeId.id)
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
            val posFoodTypeId = cursor.getColumnIndex(FoodTableBD.FOOD_TYPE_ID)
            val posFoodKcal = cursor.getColumnIndex(FoodTableBD.FOOD_KCAL)
            val posFoodProtein = cursor.getColumnIndex(FoodTableBD.FOOD_PROTEIN)
            val posFoodFat = cursor.getColumnIndex(FoodTableBD.FOOD_FAT)
            val posFoodHC = cursor.getColumnIndex(FoodTableBD.FOOD_HC)
            val posFoodTypeName = cursor.getColumnIndex(FoodTypeTableBD.FOOD_TYPE)


            val id = cursor.getLong(posId)
            val foodName = cursor.getString(posFoodName)
            val foodTypeId= cursor.getLong(posFoodTypeId)
            val kcal = cursor.getInt(posFoodKcal)
            val protein = cursor.getDouble(posFoodProtein)
            val fat = cursor.getDouble(posFoodFat)
            val carbohydrate = cursor.getDouble(posFoodHC)
            val foodTypeName = cursor.getString(posFoodTypeName)

            val type = Type(foodTypeName, foodTypeId)

            return Food(foodName, type, kcal, protein, fat, carbohydrate, id)
        }
    }
}