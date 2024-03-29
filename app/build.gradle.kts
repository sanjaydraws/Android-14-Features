plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}


val versionMajor = 1
val versionMinor = 0
val versionPatch = 0
val appVersion = "${versionMajor}.${versionMinor}.${versionPatch}"
val appVersionCode = versionMajor * 1000 + versionMinor * 100 + versionPatch

android {
    namespace = "com.sanjaydraws.android14features"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sanjaydraws.android14features"
        minSdk = 24
        targetSdk = 34
        versionCode = appVersionCode
        versionName = appVersion

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
    buildFeatures {
        //noinspection DataBindingWithoutKapt
        dataBinding  = true
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

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}