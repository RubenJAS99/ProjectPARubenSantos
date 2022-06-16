package rjas.projectparubensantos

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DataBaseTest {
    fun appContext() =
        InstrumentationRegistry.getInstrumentation().targetContext

    private fun getWritableDatabase(): SQLiteDatabase {
        val openHelper = BDappOpenHelper(appContext())
        return openHelper.writableDatabase
    }

    private fun insertUser(db: SQLiteDatabase, user: User) {
        user.id = UserTableBD(db).insert(user.toContentValues())
        assertNotEquals(0, user.id)
    }

    @Before
    fun deleteDataBase() {
        appContext().deleteDatabase(BDappOpenHelper.NAME)
    }

    @Test
    fun canOpenDataBase() {
        val openHelper = BDappOpenHelper(appContext())
        val db = openHelper.readableDatabase

        assertTrue(db.isOpen)

        db.close()
    }

    @Test
    fun canInsertUser() {
        val db = getWritableDatabase()


        val user = User("Ruben", 72.0,164,1.2,"Bulk")
        user.id = UserTableBD(db).insert(user.toContentValues())

        assertNotEquals(0, user.id)

        db.close()
    }

    @Test
    fun canModifyUser() {
        val db = getWritableDatabase()

        val user = User("Ruben", 72.0,164,1.2,"Bulk")
        insertUser(db, user)

        user.weight = 70.0
        user.period = "Cut"

        val userModified = UserTableBD(db).update(
            user.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${user.id}"))

        assertEquals(1, userModified)

        db.close()
    }

    @Test
    fun canDeleteUser() {
        val db = getWritableDatabase()

        val user = User("Ruben", 72.0,164,1.2,"Bulk")
        insertUser(db, user)

        val userDeleted = UserTableBD(db).delete(
            "${BaseColumns._ID}=?",
            arrayOf("${user.id}"))

        assertEquals(1, userDeleted)

        db.close()
    }
}