object Testing {
    private const val junitVersion = "4.13.2"
    const val junit4 = "junit:junit:$junitVersion"

    private const val junitAndroidExtVersion = "1.1.5"
    const val junitAndroidExt = "androidx.test.ext:junit:$junitAndroidExtVersion"

    private const val coroutinesTestVersion = "1.7.3"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesTestVersion"

    private const val truthVersion = "1.1.5"
    const val truth = "com.google.truth:truth:$truthVersion"

    private const val mockkVersion = "1.13.8"
    const val mockk = "io.mockk:mockk:$mockkVersion"
    const val mockkAndroid = "io.mockk:mockk-android:$mockkVersion"

    private const val turbineVersion = "1.0.0"
    const val turbine = "app.cash.turbine:turbine:$turbineVersion"

    const val composeUiTest = "androidx.compose.ui:ui-test-junit4:${Compose.composeVersion}"

    const val hiltTesting = "com.google.dagger:hilt-android-testing:${DaggerHilt.version}"

    private const val testRunnerVersion = "1.5.2"
    const val testRunner = "androidx.test:runner:$testRunnerVersion"


}