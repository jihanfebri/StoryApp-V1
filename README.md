# 📱 Story App

A modern Android application for sharing and viewing stories, built with the latest Android development practices and libraries.

## 🌟 Features

- 📝 User Authentication (Login/Register)
- 📸 Story Creation and Sharing
- 📱 Story Feed with Pull-to-Refresh
- 🖼️ Image Upload Support
- 💾 Offline Support with Local Caching
- 🎨 Modern Material Design UI
- 🌐 RESTful API Integration

## 🛠️ Tech Stack

- **Language:** Kotlin
- **Architecture:** MVVM (Model-View-ViewModel)
- **Dependency Injection:** Manual Injection
- **Network:** Retrofit2 & OkHttp3
- **Image Loading:** Glide
- **Local Storage:** 
  - Room Database
  - DataStore Preferences
  - Shared Preferences (Krate)
- **Asynchronous Programming:** Coroutines
- **UI Components:**
  - Material Design Components
  - CircleImageView
  - Lottie Animations
  - SwipeRefreshLayout
- **Logging:** Timber

## 🚀 Getting Started

### Prerequisites

- Android Studio Arctic Fox or later
- JDK 8
- Android SDK API 34
- Gradle 8.0+

### Installation

1. Clone the repository:
```bash
git clone https://github.com/jihanfebri/StoryApp-V1.git
```

2. Open the project in Android Studio

3. Sync the project with Gradle files

4. Run the app on an emulator or physical device

## 🏗️ Project Structure

The project follows a clean architecture approach with the following main components:

- **data:** Contains repositories, data sources, and models
- **di:** Dependency injection modules
- **ui:** Activities, fragments, and ViewModels
- **utils:** Utility classes and extensions
- **network:** API service interfaces and network models

## 🔑 API Configuration

The app uses the Dicoding Story API. The base URL is configured in the build.gradle.kts file:
```kotlin
buildConfigField("String", "API_URL", "\"https://story-api.dicoding.dev/v1/\"")
```

## 📱 Minimum Requirements

- Android 7.0 (API Level 24) or higher
- Internet connection for story upload and feed refresh

## 🛠️ Development Setup

1. Make sure all dependencies are properly synced
2. Build the project using Android Studio
3. Run tests to ensure everything is working correctly

## 📝 Dependencies

Key dependencies include:
- AndroidX Core KTX
- Material Design Components
- Retrofit & OkHttp
- Room Database
- Glide
- Coroutines
- Lifecycle Components
- DataStore
- Timber

For a complete list of dependencies, please check the `build.gradle.kts` file.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

---

Made with ❤️ using Android Studio and Kotlin
