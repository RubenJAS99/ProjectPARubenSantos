package rjas.projectparubensantos.food

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Type(
    var name : String,
    var id: Long = -1
) {
    fun toContentValues(): ContentValues {
        val values = ContentValues()

        values.put(FoodTypeTableBD.FOOD_TYPE, name)

        return values
    }

    companion object {
        fun fromCursor(cursor: Cursor): Type {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posFoodType = cursor.getColumnIndex(FoodTypeTableBD.FOOD_TYPE)

            val id = cursor.getLong(posId)
            val name = cursor.getString(posFoodType)

            return Type(name, id)
        }
    }
}