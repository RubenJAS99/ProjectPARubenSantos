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
        assertNotEquals(-1, user.id)
    }
    private fun insertFood(db: SQLiteDatabase, food: Food) {
        food.id = FoodTableBD(db).insert(food.toContentValues())
        assertNotEquals(-1, food.id)
    }
    private fun insertProgress(db: SQLiteDatabase, progress: Progress) {
        progress.id = ProgressTableBD(db).insert(progress.toContentValues())
        assertNotEquals(0, progress.id)
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
        insertUser(db, user)

        db.close()
    }

    @Test
    fun canInsertFood() {
        val db = getWritableDatabase()

        val food = Food("Rice", "carbohydrate", 348, 6.9, 1.0, 77.8)
        insertFood(db, food)

        db.close()
    }
    @Test
    fun canInsertProgress() {
        val db = getWritableDatabase()

        val progress = Progress(72.0,71.8,"Cut")
        insertProgress(db, progress)

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
    fun canModifyFood() {
        val db = getWritableDatabase()

        val food = Food("Rice", "carbohydrate", 348, 6.9, 1.0, 77.8)
        insertFood(db, food)

        food.kcal = 351
        food.protein = 7.3

        val foodModified = FoodTableBD(db).update(
            food.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${food.id}"))

        assertEquals(1, foodModified)

        db.close()
    }
    @Test
    fun canModifyProgress() {
        val db = getWritableDatabase()

        val progress = Progress(72.0,71.8,"Cut")
        insertProgress(db, progress)

        progress.currentWeight = 71.9

        val progressModified = ProgressTableBD(db).update(
            progress.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${progress.id}"))

        assertEquals(0, progressModified)

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

    @Test
    fun canDeleteFood() {
        val db = getWritableDatabase()

        val food = Food("Rice", "carbohydrate", 348, 6.9, 1.0, 77.8)
        insertFood(db, food)

        val foodDeleted = FoodTableBD(db).delete(
            "${BaseColumns._ID}=?",
            arrayOf("${food.id}"))

        assertEquals(1, foodDeleted)

        db.close()
    }

    @Test
    fun canReadUsers() {
        val db = getWritableDatabase()

        val user = User("Ruben", 72.0,164,1.2,"Bulk")
        insertUser(db, user)

        val cursor = UserTableBD(db).query(
            UserTableBD.ALL_COLUMNS,
            "${BaseColumns._ID}=?",
            arrayOf("${user.id}"),
            null,
            null,
            null
        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val userBD = User.fromCursor(cursor)

        assertEquals(user, userBD)

        db.close()
    }

    @Test
    fun canReadFoods() {
        val db = getWritableDatabase()

        val food = Food("Rice", "carbohydrate", 348, 6.9, 1.0, 77.8)
        insertFood(db, food)

        val cursor = FoodTableBD(db).query(
            FoodTableBD.ALL_COLUMNS,
            "${BaseColumns._ID}=?",
            arrayOf("${food.id}"),
            null,
            null,
            null
        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val foodBD = Food.fromCursor(cursor)

        assertEquals(food, foodBD)

        db.close()
    }
}