package com.example.dailyexpenseapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.dailyexpenseapp.R
import com.example.dailyexpenseapp.ui.ExpenseViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun AddExpenseDialog(
    viewModel: ExpenseViewModel,
    onDismiss: () -> Unit
) {
    var amount by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Food") }
    var date by remember { mutableStateOf(LocalDate.now().toString()) }
    var note by remember { mutableStateOf("") }
    var categoryExpanded by remember { mutableStateOf(false) }

    val categories = listOf(
        "Food",
        "Transport",
        "Entertainment",
        "Utilities",
        "Healthcare",
        "Education",
        "Shopping",
        "Other"
    )

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .background(
                    MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.large
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = stringResource(R.string.add_expense),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Amount Input
                OutlinedTextField(
                    value = amount,
                    onValueChange = { amount = it },
                    label = { Text(stringResource(R.string.expense_amount)) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardType = androidx.compose.ui.text.input.KeyboardType.Decimal,
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Category Dropdown
                Box {
                    OutlinedTextField(
                        value = category,
                        onValueChange = {},
                        label = { Text(stringResource(R.string.expense_category)) },
                        modifier = Modifier.fillMaxWidth(),
                        readOnly = true,
                        enabled = false
                    )
                    DropdownMenu(
                        expanded = categoryExpanded,
                        onDismissRequest = { categoryExpanded = false },
                        modifier = Modifier.fillMaxWidth(0.95f)
                    ) {
                        categories.forEach { cat ->
                            DropdownMenuItem(
                                text = { Text(cat) },
                                onClick = {
                                    category = cat
                                    categoryExpanded = false
                                }
                            )
                        }
                    }
                    OutlinedButton(
                        onClick = { categoryExpanded = !categoryExpanded },
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                    ) {
                        Text(category)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Date Input
                OutlinedTextField(
                    value = date,
                    onValueChange = { date = it },
                    label = { Text(stringResource(R.string.expense_date)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Note Input
                OutlinedTextField(
                    value = note,
                    onValueChange = { note = it },
                    label = { Text(stringResource(R.string.expense_note)) },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 3
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.End)
                ) {
                    OutlinedButton(onClick = onDismiss) {
                        Text(stringResource(R.string.cancel))
                    }
                    Button(
                        onClick = {
                            if (amount.isNotEmpty()) {
                                viewModel.addExpense(
                                    amount = amount.toDoubleOrNull() ?: 0.0,
                                    category = category,
                                    date = date,
                                    note = note
                                )
                                onDismiss()
                            }
                        }
                    ) {
                        Text(stringResource(R.string.save))
                    }
                }
            }
        }
    }
}
