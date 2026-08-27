# Daily Expense App

A simple and elegant Android application built with Kotlin and Jetpack Compose for tracking daily expenses.

## Features

- **Add Expenses**: Record daily expenses with amount, category, date, and notes
- **Expense Tracking**: View all expenses in a beautiful list
- **Daily & Monthly Totals**: See your spending totals at a glance
- **Categories**: Organize expenses by predefined categories (Food, Transport, Entertainment, etc.)
- **Delete Expenses**: Remove unwanted expense entries
- **Local Storage**: All data stored locally using Room Database
- **Material Design 3**: Modern UI with Material 3 design system

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Database**: Room Database
- **Concurrency**: Coroutines
- **Architecture**: MVVM with Repository Pattern
- **Minimum SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)

## Building the Project

### Prerequisites
- JDK 11 or higher
- Android SDK (API level 34)
- Gradle 7.6 or higher

### Build Instructions

1. Clone the repository:
```bash
git clone https://github.com/hme89271-spec/Daily-Expense-App.git
cd Daily-Expense-App
```

2. Build the project:
```bash
./gradlew build
```

3. Build and run the debug APK:
```bash
./gradlew assembleDebug
```

4. Install on a device:
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

## Project Structure

- `app/src/main/java/com/example/dailyexpenseapp/` - Main application code
  - `data/` - Database DAOs and repository
  - `model/` - Data models (Expense)
  - `ui/` - Jetpack Compose screens and view models
  - `ui/theme/` - Material 3 theming
- `app/src/main/res/` - Android resources
- `.github/workflows/` - GitHub Actions CI/CD

## Architecture

- **MVVM** with Repository Pattern
- **Jetpack Compose** for UI
- **Room Database** for persistence
- **Kotlin Coroutines** for async operations

## Features in Detail

### Add Expense
- Open the add expense dialog by clicking the FAB
- Enter amount (decimal supported)
- Select category from dropdown
- Choose date (defaults to today)
- Add optional notes
- Save the expense

### View Expenses
- All expenses displayed in reverse chronological order
- Daily and monthly totals displayed at top
- Tap delete icon to remove an expense

### Persistence
- All expenses saved to local Room database
- Data persists across app restarts

## Dependencies

- androidx.core:core-ktx:1.12.0
- androidx.compose.ui:ui:1.5.4
- androidx.compose.material3:material3:1.1.1
- androidx.room:room-runtime:2.6.1
- org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1
- androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2

## License

MIT License

## Author

Sobuj Khan
