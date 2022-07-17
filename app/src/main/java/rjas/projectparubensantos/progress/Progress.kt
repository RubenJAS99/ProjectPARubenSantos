package rjas.projectparubensantos.progress

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Progress(
    var date: String,
    var lastWeight: Double,
    var currentWeight: Double,
    var period: String,
    var id: Long = -1
) {
    fun toContentValues(): ContentValues {
        val values = ContentValues()

        values.put(ProgressTableBD.PROGRESS_DATE, date)
        values.put(ProgressTableBD.LAST_WEIGHT, lastWeight)
        values.put(ProgressTableBD.CURRENT_WEIGHT, currentWeight)
        values.put(ProgressTableBD.USER_PERIOD, period)

        return values
    }

    companion object {
        fun fromCursor(cursor: Cursor): Progress {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posProgressDate = cursor.getColumnIndex(ProgressTableBD.PROGRESS_DATE)
            val posLastWeight = cursor.getColumnIndex(ProgressTableBD.LAST_WEIGHT)
            val posCurrentWeight = cursor.getColumnIndex(ProgressTableBD.CURRENT_WEIGHT)
            val posUserPeriod = cursor.getColumnIndex(ProgressTableBD.USER_PERIOD)

            val id = cursor.getLong(posId)
            val date = cursor.getString(posProgressDate)
            val lastWeight= cursor.getDouble(posLastWeight)
            val currentWeight = cursor.getDouble(posCurrentWeight)
            val period = cursor.getString(posUserPeriod)

            return Progress(date, lastWeight, currentWeight, period, id)
        }
    }
}
