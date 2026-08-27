package com.example.dailyexpenseapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dailyexpenseapp.model.Expense

@Database(
    entities = [Expense::class],
    version = 1,
    exportSchema = false
)
abstract class ExpenseDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao

    companion object {
        @Volatile
        private var Instance: ExpenseDatabase? = null

        fun getDatabase(context: Context): ExpenseDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    ExpenseDatabase::class.java,
                    "expense_database"
                ).build().also { Instance = it }
            }
        }
    }
}
