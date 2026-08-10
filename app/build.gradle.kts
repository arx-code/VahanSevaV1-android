plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.google.devtools.ksp") // Room compiler
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.vahanseva.auto_mall"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.vahanseva.auto_mall"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

//    implementation("androidx.core:core-ktx:1.10.0")
//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.0")
//    implementation("androidx.activity:activity-compose:1.8.0")
//    implementation(platform("androidx.compose:compose-bom:2024.09.00"))
//    implementation("androidx.compose.ui:ui")
//    implementation("androidx.compose.ui:ui-graphics")
//    implementation("androidx.compose.ui:ui-tooling-preview")
//    implementation("androidx.compose.material3:material3")
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.3.0")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")
//    androidTestImplementation(platform("androidx.compose:compose-bom:2024.09.00"))
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
//    debugImplementation("androidx.compose.ui:ui-tooling")
//    debugImplementation("androidx.compose.ui:ui-test-manifest")
//

    // Base Dependencies
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2024.09.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // Compose Utility Lib
    implementation("androidx.compose.material:material-icons-extended") // Mic, Keyboard, Send Icons
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.6")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.8.2")

    // Part 1: DataStore Preferences
    implementation("androidx.datastore:datastore-preferences:1.1.1")
    implementation("androidx.navigation:navigation-runtime-ktx:2.9.8")
    implementation("androidx.navigation:navigation-compose:2.9.8")
    implementation("androidx.databinding:adapters:3.2.0-alpha11")
    implementation("androidx.camera:camera-video:1.6.1")

    // Part 2 & Part 4: Room Database + Paging 3
    val roomVersion = "2.6.1"
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    implementation("androidx.room:room-paging:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")

    val pagingVersion = "3.3.2"
    implementation("androidx.paging:paging-runtime-ktx:$pagingVersion")
    implementation("androidx.paging:paging-compose:$pagingVersion")

    // Part 4: WorkManager
    implementation("androidx.work:work-runtime-ktx:2.9.1")

    // Coroutines & JSON Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
    implementation("com.google.code.gson:gson:2.11.0") // Room TypeConverter mapping for MessageMeta

    // Testing (Unit & Coroutine Testing)
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")
    testImplementation("io.mockk:mockk:1.13.12")

    // Android Instrumentation Testing
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.09.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    testImplementation("app.cash.turbine:turbine:1.0.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")

    // Mockito for Kotlin (includes mock-maker-inline for final classes)
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.2.0")
    testImplementation("org.mockito:mockito-inline:5.2.0")

    // Hilt Dependency Injection
    val hiltVersion = "2.48"
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    ksp("com.google.dagger:hilt-compiler:$hiltVersion")

    // Retrofit 2 & OkHttp
    val retrofitVersion = "2.11.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

}