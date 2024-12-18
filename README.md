
# **Currency Converter Application**

## **Overview**

The Currency Converter Application is a simple Android app built using **Kotlin** and **MVVM architecture**. It allows users to:
- Input an amount.
- Select a currency to **convert from** and **convert to**.
- Fetch **live exchange rates** from an API and display the converted amount.

The app leverages:
- **Retrofit** for API communication.
- **Dependency Injection** using Dagger/Hilt (as inferred from `AppModule` and `ViewModelModule`).
- Clean architecture principles, ensuring separation of concerns.
- MVVM design pattern for handling UI and data management.

---

## **Project Structure**

The project follows a modular structure for clean code organization:

```plaintext
com.example.currencyconverterapplication
│
├── di                   # Dependency Injection setup
│   ├── AppModule.kt
│   ├── MyApplication.kt
│   └── ViewModelModule.kt
│
├── helper               # Utility classes
│   ├── Endpoint.kt
│   ├── Resource.kt
│   ├── SingleLiveEvent.kt
│   └── Utility.kt
│
├── model                # Data models
│   └── ExchangeRateResponseModel.kt
│
├── network              # API setup
│   ├── ApiDataSource.kt
│   ├── BaseDataSource.kt
│   └── RetrofitClient.kt
│
├── services             # API service interface
│   └── CurrencyApiService.kt
│
├── ui.theme             # Theming for the UI
│
├── view                 # UI components
│   └── MainActivity.kt
│
└── view_model           # ViewModels and Repository
    ├── CurrencyRepository.kt
    └── CurrencyViewModel.kt
```

---

## **Steps to Build and Run**

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd Currency_Converter_Application
   ```

2. **Setup API Key**
   - Replace `<YOUR_API_KEY>` in the `build.gradle.kts` file or wherever the base URL/API key is set.

   Example:
   ```gradle
   buildTypes {
        debug {
            buildConfigField("String", "API_KEY", "\"YOUR_API_KEY2\"")
        }
        release {
            buildConfigField("String", "API_KEY", "\"YOUR_API_KEY\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
   ```

3. **Build the Project**
   - Open the project in **Android Studio**.
   - Ensure all dependencies are synced by clicking **"Sync Now"** in the Gradle panel.

4. **Run the App**
   - Connect an Android device or use the emulator.
   - Build and run the app using the "Run" button in Android Studio.

---

## **Challenges or Notes**

1. **API Integration:**
   - The app integrates a public currency exchange API.
   - Ensure you have a valid API key and the API allows sufficient free requests.

2. **Error Handling:**
   - Handled cases like invalid inputs, API failures, and network issues.

3. **Architecture:**
   - Clean architecture principles were followed for maintainability.
   - The **MVVM** pattern separates UI logic, business logic, and data management.

4. **Improvements:**
   - UI could be enhanced with animations and better styling.
   - Added unit tests for core functionality like `CurrencyRepository` and API responses.

5. **Dependencies:**
   - Retrofit: For API calls.
   - LiveData: To observe data changes in ViewModel.
   - Dagger/Hilt: For dependency injection.

---

## **Technologies Used**

- **Language**: Kotlin
- **Architecture**: MVVM + Clean Architecture
- **Libraries**:
  - Retrofit
  - LiveData/ViewModel
  - Dagger/Hilt
  - Coroutines (for asynchronous operations)

---

## **Next Steps**
- Write unit tests to cover critical app logic.
- Enhance UI/UX with Material Design components.
- Add support for multiple APIs.

---

**Thank you for using the Currency Converter Application!**
