// import com.android.build.api.dsl.Packaging
//
// plugins {
//     alias(libs.plugins.android.application)
//     alias(libs.plugins.jetbrains.kotlin.android)
// }
//
// android {
//     namespace = "com.example.yasamkocum"
//     compileSdk = 35
//
//     defaultConfig {
//         applicationId = "com.example.yasamkocum"
//         minSdk = 24
//         targetSdk = 35
//         versionCode = 1
//         versionName = "1.0"
//
//         testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//     }
//
//     buildTypes {
//         release {
//             isMinifyEnabled = false
//             proguardFiles(
//                 getDefaultProguardFile("proguard-android-optimize.txt"),
//                 "proguard-rules.pro"
//             )
//         }
//     }
//     compileOptions {
//         sourceCompatibility = JavaVersion.VERSION_1_8
//         targetCompatibility = JavaVersion.VERSION_1_8
//     }
//     kotlinOptions {
//         jvmTarget = "1.8"
//     }
//     packaging {
//         resources {
//             excludes += "META-INF/LICENSE-LGPL-3.txt"
//             pickFirsts += "META-INF/LICENSE-LGPL-3.txt"
//
//         }
//     }
//
// }
//
// dependencies {
//
//     implementation(libs.androidx.core.ktx)
//     implementation(libs.androidx.appcompat)
//     implementation(libs.material)
//     implementation(libs.androidx.activity)
//     implementation(libs.androidx.constraintlayout)
//     testImplementation(libs.junit)
//     androidTestImplementation(libs.androidx.junit)
//     androidTestImplementation(libs.androidx.espresso.core)
//     //implementation(libs.generativeai)
//     implementation("com.google.ai.client.generativeai:generativeai:0.9.0")
//     implementation("com.vladsch.flexmark:flexmark-all:0.64.0")
//
// }

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.yasamkocum"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.yasamkocum"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
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

    // Kotlin DSL formatında packagingOptions
    packagingOptions {
        pickFirst("META-INF/*")
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
    implementation("com.google.ai.client.generativeai:generativeai:0.9.0")
    implementation("com.vladsch.flexmark:flexmark-all:0.64.0")
    implementation("org.commonmark:commonmark:0.21.0")
    implementation("io.github.dakshsemwal:mdparserkitcore:1.0.1")
}
/*
3 files found with path 'META-INF/DEPENDENCIES'.
Adding a packaging block may help, please refer to
https://developer.android.com/reference/tools/gradle-api/8.5/com/android/build/api/dsl/Packaging
for more information
 */
