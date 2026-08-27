package com.example.dailyexpenseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModelProvider
import com.example.dailyexpenseapp.data.ExpenseDatabase
import com.example.dailyexpenseapp.data.ExpenseRepository
import com.example.dailyexpenseapp.ui.ExpenseViewModel
import com.example.dailyexpenseapp.ui.ExpenseViewModelFactory
import com.example.dailyexpenseapp.ui.screens.ExpenseListScreen
import com.example.dailyexpenseapp.ui.theme.DailyExpenseAppTheme

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: ExpenseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize database and repository
        val database = ExpenseDatabase.getDatabase(this)
        val repository = ExpenseRepository(database.expenseDao())
        val factory = ExpenseViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(ExpenseViewModel::class.java)

        setContent {
            DailyExpenseAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    ExpenseListScreen(
                        viewModel = viewModel,
                        onAddExpenseClick = {}
                    )
                }
            }
        }
    }
}
