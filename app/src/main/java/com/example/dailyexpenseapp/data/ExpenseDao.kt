package com.example.dailyexpenseapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.dailyexpenseapp.model.Expense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Insert
    suspend fun insertExpense(expense: Expense)

    @Update
    suspend fun updateExpense(expense: Expense)

    @Delete
    suspend fun deleteExpense(expense: Expense)

    @Query("SELECT * FROM expenses ORDER BY date DESC, timestamp DESC")
    fun getAllExpenses(): Flow<List<Expense>>

    @Query("SELECT * FROM expenses WHERE date = :date ORDER BY timestamp DESC")
    fun getExpensesByDate(date: String): Flow<List<Expense>>

    @Query("SELECT * FROM expenses WHERE date LIKE :monthYear || '%' ORDER BY date DESC, timestamp DESC")
    fun getExpensesByMonth(monthYear: String): Flow<List<Expense>>

    @Query("SELECT SUM(amount) FROM expenses WHERE date = :date")
    fun getDailyTotal(date: String): Flow<Double?>

    @Query("SELECT SUM(amount) FROM expenses WHERE date LIKE :monthYear || '%'")
    fun getMonthlyTotal(monthYear: String): Flow<Double?>
}
