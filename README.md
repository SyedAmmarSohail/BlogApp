<div align="center">

  <h1>Blog App</h1>
  
  <p>
    Blog app where you find latest, trending and featured blogs.
  </p>
  
  
<!-- Badges -->
![kotlin](https://img.shields.io/badge/Kotlin-1.6.10-white.svg?style=for-the-badge&labelColor=7E57C2)
![compose](https://img.shields.io/badge/Compose-1.2.0-white.svg?style=for-the-badge&labelColor=5C6BC0)
![Hilt](https://img.shields.io/badge/Hilt-2.40-white.svg?style=for-the-badge&labelColor=42A5F5)
![Coroutines](https://img.shields.io/badge/Coroutines-1.6.0-white.svg?style=for-the-badge&labelColor=26C6DA)
![minSdkVersion](https://img.shields.io/badge/MinSdkVersion-21-white.svg?style=for-the-badge&labelColor=26A69A)
![targetSdkVersion](https://img.shields.io/badge/TargetSdkVersion-31-white.svg?style=for-the-badge&labelColor=66BB6A)
![MVI](https://img.shields.io/badge/CleanCode-MVI-white.svg?style=for-the-badge&labelColor=FFCA28)
   
</div>

<br />

<!-- Table of Contents -->
# Table of Contents

- [Screenshots](#screenshots)
- [Tech Stack](#tech-stack)
- [Installation](#installation)
- [Architecture](#architecture)
- [Architecture Diagram](#architecture-diagram)
- [Project Tree](#project-tree)
- [Test Cases](#test-cases)


<!-- About the Project -->
## About the Project


<!-- Screenshots -->
### Screenshots

[<img src="https://drive.google.com/uc?export=view&id=1iVYAJWRPDuFnQ4yjDMLhxRY8HwO7CUa6" align="left"
width="200"
    hspace="10" vspace="10">](https://drive.google.com/uc?export=view&id=1iVYAJWRPDuFnQ4yjDMLhxRY8HwO7CUa6) 
[<img src="https://drive.google.com/uc?export=view&id=1nJPKa-znCY86dDAmW2yTtlDx6vxyNK_-" align="center"
width="200"
    hspace="10" vspace="10">](https://drive.google.com/uc?export=view&id=1nJPKa-znCY86dDAmW2yTtlDx6vxyNK_-) 
[<img src="https://drive.google.com/uc?export=view&id=103t-t7zQM0b3x-Re9bA_nOYWBHpB9UM6" align="center"
width="200"
hspace="10" vspace="10">](https://drive.google.com/uc?export=view&id=103t-t7zQM0b3x-Re9bA_nOYWBHpB9UM6) 
[<img src="https://drive.google.com/uc?export=view&id=1JLdr01WIbrKQFxy4jKscXUgzVxj89K_e" align="center"
width="200"
hspace="10" vspace="10">](https://drive.google.com/uc?export=view&id=1JLdr01WIbrKQFxy4jKscXUgzVxj89K_e) 


<!-- TechStack -->
### Tech Stack
    
* [Kotlin](https://kotlinlang.org/docs/home.html)
* [Compose](https://developer.android.com/jetpack/compose/documentation)
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
* [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState)
* [Moshi](https://github.com/square/moshi)
* [Coroutines](https://developer.android.com/kotlin/coroutines) 
* [Retrofit](https://square.github.io/retrofit/) 
* [Mockk](https://mockk.io/)
* [Truth](https://truth.dev/)
* [Custom Pagination]()

Mutable state is used in this project instead of Flows.


### Installation

**Download:**

    $ git clone url

Import Project by Android Studio Menu > File > Import Project.

**Release:**

This app is production ready, only need to add your keystore path, password and alias in build.gradle file located under the app folder.


<!-- Architecture -->
### Architecture

**AppModule:**

Used layer-based clean architecture in which include data, domain and presentation layer.

- **Data layer -** Manages application data eg. retrieve data from the network, manage local data cache

- **Domain layer -** Contains business logic with separate usecases

- **Presentation layer -** Presents data to a screen and handle user interactions

**BuildSrc:**

Puts every dependencies in one place with respect to its classes, and use it by calling the dependency with its class.


<!-- Architecture Diagram -->
### Architecture Diagram

<div align="center"> 
  <img src="https://drive.google.com/uc?export=view&id=1ffusRxcx-fC6Iw-1kRz93cmvrCBD-ehi" alt="screenshot" /> 
</div>

  
<!-- Project tree -->
### Project tree

```text
.
├── app
    ├── di
        ├── AppModule
    ├── navigation
        ├── Route
    ├── ui
        ├── theme
    ├── BlogApp
    ├── MainActivity
├── blog
    ├── data
        ├── di
            ├── BlogDataModule
        ├── local
            ├── entity
                ├── BlogEntity
            ├── BlogDao
            ├── BlogDatabase
            ├── Converters    
        ├── mapper
            ├── BlogMapper.kt
        ├── remote
            ├── dto
                ├── Article
                ├── BlogDto
            ├── BlogApi
        ├── repository
            ├── BlogRepositoryImp
    ├── domain
        ├── di
            ├── BlogDomainModule
        ├── model
            ├── BlogModel
        ├── repository
            ├── BlogRepository
        ├── use_case
            ├── BlogUseCase
            ├── GetBlog
            ├── SearchBlog
            ├── StoreBlogs
    ├── presentation
        ├── blog_detail
            ├── BlogDetailScreen.kt
        ├── blog_overview
            ├── components
                ├── CardItem.kt
                ├── SearchTextField.kt
            ├── Blog
            ├── BlogListScreen.kt
            ├── BlogOverviewEvent
            ├── BlogOverviewScreen.kt    
            ├── BlogOverviewState
            ├── BlogOverviewViewModel
            ├── TabItem
├── buildSrc
    ├── AndroidX
    ├── Build
    ├── Coil
    ├── Compose
    ├── Coroutines
    ├── DaggerHilt
    ├── Google
    ├── Kotlin
    ├── Modules
    ├── Moshi
    ├── ProjectConfig
    ├── Retrofit
    ├── Testing
    ├── ViewPager
├── core
    ├── data
        ├── preferences
    ├── domain    
        ├── model
        ├── preferences
    ├── extension
        ├── URLExtension
    ├── util
        ├── Constants
├── core-ui                           
    ├── component
        ├── ActionAppBar.kt
        ├── ComposeVerticalList.kt
        ├── ImagePlaceHolder.kt
        ├── LinePlaceHolder.kt
        ├── ShimmerAnimation.kt
        ├── TextWithIcon.kt
    ├── Color.kt
    ├── Dimensions.kt
    ├── FontSize.kt    
├── onboarding
    ├── composables
        ├── OnBoardingButtons.kt
        ├── OnboardingContent.kt
        ├── OnboardingDotsIndicator.kt
        ├── OnBoardingScreenImage.kt  
    ├── properties
        ├── OnBoardingProperties     
    ├── state
        ├── OnBoardingState
    ├── OnBoardingModel.kt
    ├── OnBoardingScreen.kt
├── profile
    ├── profile_presentation
        ├── ProfileScreen.kt
        ├── User.kt
└── .gitignore

```  


### Test Cases

**Unit Test:**

To run the unit tests for repository go to the **BlogRepositoryImpTest** class under the **test** folder in **blog_data module** and run the tests.
To run the unit tests for view model go to the **BlogOverviewViewModelTest** class under the **test** folder in **blog_presentation module** and run the tests.

**End to End UI Test:**

To run the end to end ui tests for the app go to the **BlogOverviewE2ETest** class under the **androidTest** folder in **app module** and run the tests.


<!-- Contact -->
## Contact

Syed Ammar Sohail - ammarsohail321@gmail.com
