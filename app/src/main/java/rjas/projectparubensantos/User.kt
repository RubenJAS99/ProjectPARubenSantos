package rjas.projectparubensantos

import android.content.ContentValues

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
}
