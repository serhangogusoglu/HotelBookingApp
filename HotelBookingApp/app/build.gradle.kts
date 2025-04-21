plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.hotelbookingapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.hotelbookingapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Room Database
    implementation ("androidx.room:room-runtime:2.5.0") // Room kütüphanesi
    annotationProcessor ("androidx.room:room-compiler:2.5.0") // Room için annotation processor

    // Eğer Kotlin kullanıyorsanız kapt kullanmanız gerekebilir
    kapt ("androidx.room:room-compiler:2.5.0")

    // LiveData ve ViewModel için bağımlılıklar (optional)
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    // RecyclerView
    implementation ("androidx.recyclerview:recyclerview:1.2.1")

    // Material Design için bağımlılık
    implementation ("com.google.android.material:material:1.8.0")

    // Glide (Resim yükleme için)
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    kapt ("com.github.bumptech.glide:compiler:4.15.1")
}