package com.example.dailyexpenseapp.data

import com.example.dailyexpenseapp.model.Expense
import kotlinx.coroutines.flow.Flow

class ExpenseRepository(private val expenseDao: ExpenseDao) {
    fun getAllExpenses(): Flow<List<Expense>> = expenseDao.getAllExpenses()

    fun getExpensesByDate(date: String): Flow<List<Expense>> = expenseDao.getExpensesByDate(date)

    fun getExpensesByMonth(monthYear: String): Flow<List<Expense>> = expenseDao.getExpensesByMonth(monthYear)

    fun getDailyTotal(date: String): Flow<Double?> = expenseDao.getDailyTotal(date)

    fun getMonthlyTotal(monthYear: String): Flow<Double?> = expenseDao.getMonthlyTotal(monthYear)

    suspend fun insertExpense(expense: Expense) = expenseDao.insertExpense(expense)

    suspend fun updateExpense(expense: Expense) = expenseDao.updateExpense(expense)

    suspend fun deleteExpense(expense: Expense) = expenseDao.deleteExpense(expense)
}
