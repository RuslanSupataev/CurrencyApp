plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {

    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "kg.ruslansupataev.currencyapp"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            // here you can change source for prod version of application
            val baseUrl = findProperty("BASE_URL") as String
            buildConfigField("String", "BASE_URL", baseUrl)
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = false
            val baseUrl = findProperty("BASE_URL") as String
            buildConfigField("String", "BASE_URL", baseUrl)
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.android.material)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.contraintlayout)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigation.fragment)

    implementation(libs.squareup.retrofit2.retrofit)
    implementation(libs.squareup.retrofit2.converter)

    implementation(libs.bundles.koin)

    implementation(libs.bundles.room)
    kapt(libs.androidx.room.compiler)

}