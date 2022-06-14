package rjas.projectparubensantos

import android.content.ContentValues
import android.provider.BaseColumns

data class user(var id: Long, var nome: String) {

    fun toContentValues() : ContentValues {
        val values = ContentValues()
        values.put(UserTableBD.USER_NAME, nome)

        return values
    }

}