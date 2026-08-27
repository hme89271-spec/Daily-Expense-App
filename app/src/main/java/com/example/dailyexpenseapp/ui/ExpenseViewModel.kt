package com.example.dailyexpenseapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.dailyexpenseapp.data.ExpenseRepository
import com.example.dailyexpenseapp.model.Expense
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

data class ExpenseUiState(
    val expenses: List<Expense> = emptyList(),
    val dailyTotal: Double = 0.0,
    val monthlyTotal: Double = 0.0,
    val selectedDate: String = LocalDate.now().toString(),
    val isLoading: Boolean = false
)

class ExpenseViewModel(private val repository: ExpenseRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(ExpenseUiState())
    val uiState: StateFlow<ExpenseUiState> = _uiState.asStateFlow()

    init {
        loadExpenses()
    }

    private fun loadExpenses() {
        viewModelScope.launch {
            val today = LocalDate.now().toString()
            val monthYear = YearMonth.now().format(DateTimeFormatter.ofPattern("yyyy-MM"))

            repository.getAllExpenses().collect { expenses ->
                _uiState.update { it.copy(expenses = expenses) }
            }

            repository.getDailyTotal(today).collect { total ->
                _uiState.update { it.copy(dailyTotal = total ?: 0.0) }
            }

            repository.getMonthlyTotal(monthYear).collect { total ->
                _uiState.update { it.copy(monthlyTotal = total ?: 0.0) }
            }
        }
    }

    fun addExpense(amount: Double, category: String, date: String, note: String) {
        viewModelScope.launch {
            val expense = Expense(
                amount = amount,
                category = category,
                date = date,
                note = note
            )
            repository.insertExpense(expense)
            loadExpenses()
        }
    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            repository.deleteExpense(expense)
            loadExpenses()
        }
    }

    fun updateSelectedDate(date: String) {
        _uiState.update { it.copy(selectedDate = date) }
        loadExpenses()
    }
}

class ExpenseViewModelFactory(private val repository: ExpenseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExpenseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ExpenseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
