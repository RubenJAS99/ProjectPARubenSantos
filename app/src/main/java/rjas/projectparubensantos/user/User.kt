package rjas.projectparubensantos.user

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class User(
    var name : String,
    var weight: Double,
    var height: Int,
    var activity_level: Double,
    var period: String,
    var id: Long = -1
) {
    fun toContentValues(): ContentValues {
        val values = ContentValues()

        values.put(UserTableBD.USER_NAME, name)
        values.put(UserTableBD.USER_WEIGHT, weight)
        values.put(UserTableBD.USER_HEIGHT, height)
        values.put(UserTableBD.USER_ACTIVITY_LEVEL, activity_level)
        values.put(UserTableBD.USER_PERIOD, period)

        return values
    }

    companion object {
        fun fromCursor(cursor: Cursor): User {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posUserName = cursor.getColumnIndex(UserTableBD.USER_NAME)
            val posUserWeight = cursor.getColumnIndex(UserTableBD.USER_WEIGHT)
            val posUserHeight = cursor.getColumnIndex(UserTableBD.USER_HEIGHT)
            val posUserActivityLevel = cursor.getColumnIndex(UserTableBD.USER_ACTIVITY_LEVEL)
            val posUserPeriod = cursor.getColumnIndex(UserTableBD.USER_PERIOD)


            val id = cursor.getLong(posId)
            val name = cursor.getString(posUserName)
            val weight= cursor.getDouble(posUserWeight)
            val height = cursor.getInt(posUserHeight)
            val activityLevel = cursor.getDouble(posUserActivityLevel)
            val period = cursor.getString(posUserPeriod)

            return User(name, weight, height, activityLevel, period, id)
        }
    }
}
