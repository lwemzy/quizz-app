# QuizApp

QuizApp is an Android application built with Kotlin that allows users to take quizzes using questions and answers stored in a Room Database. It features a simple UI and uses Jetpack Navigation to transition between screens.

## Features

- Preloaded multiple-choice questions on app launch
- Local data persistence using Room Database
- Kotlin coroutines for asynchronous database operations
- Navigation between fragments using Jetpack Navigation Component

## HomeFragment Overview

The `HomeFragment` is the entry point of the app where the user can begin the quiz.

### Key Functionalities:

- Displays a `Start` button (`btn_start`)
- On click:
  - Checks if the question database is empty
  - If empty, seeds the database with a list of 15 questions and their corresponding answers
  - Navigates to the quiz screen using NavController

### Technologies Used

- **Kotlin**
- **Android Jetpack**
  - Room
  - Navigation
  - ViewModel / Fragment lifecycle
- **Coroutines**

## Getting Started

### Prerequisites

- Android Studio Flamingo or later
- Android SDK 33+
- Gradle 7.0+

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/lwemzy/quizz-app.git
