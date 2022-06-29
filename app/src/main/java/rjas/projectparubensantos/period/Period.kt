package rjas.projectparubensantos.period

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Period(
    var name : String,
    var id: Long = -1
) {
    fun toContentValues(): ContentValues {
        val values = ContentValues()

        values.put(PeriodTableBD.USER_PERIOD, name)

        return values
    }

    companion object {
        fun fromCursor(cursor: Cursor): Period {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posUserPeriod = cursor.getColumnIndex(PeriodTableBD.USER_PERIOD)

            val id = cursor.getLong(posId)
            val name = cursor.getString(posUserPeriod)

            return Period(name, id)
        }
    }
}