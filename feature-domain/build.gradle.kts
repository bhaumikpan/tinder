plugins {
    kotlin("android")
    kotlin("kapt")
    id("com.android.library")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.feature_data"
    compileSdk = 34

    defaultConfig {
        minSdk = 28

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

val networkDependencies = mapOf(
    "retrofit" to "com.squareup.retrofit2:retrofit:${rootProject.ext.get("retrofitVersion")}",
    "retrofitGsonConverter" to "com.squareup.retrofit2:converter-gson:${rootProject.ext.get("retrofitVersion")}",
    "retrofitRxJavaAdapter" to "com.squareup.retrofit2:adapter-rxjava2:${rootProject.ext.get("retrofitVersion")}",
    "okHttp3" to "com.squareup.okhttp3:okhttp:${rootProject.ext.get("okHttpVersion")}",
    "okHttp3Logging" to "com.squareup.okhttp3:logging-interceptor:${rootProject.ext.get("okHttpVersion")}"
)

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(networkDependencies["retrofit"]!!)
    implementation(networkDependencies["retrofitGsonConverter"]!!)
    implementation(networkDependencies["retrofitRxJavaAdapter"]!!)
    implementation(networkDependencies["okHttp3"]!!)
    implementation(networkDependencies["okHttp3Logging"]!!)

    // Hilt
    implementation(libs.hilt.android)
    kapt("com.google.dagger:hilt-android-compiler:${rootProject.ext.get("hilt_version")}")
    testImplementation (libs.hilt.android.testing)
    kaptTest ("com.google.dagger:hilt-compiler:${rootProject.ext.get("hilt_version")}")


    implementation(libs.gson)

    implementation(project(":network-data"))

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Extra
    implementation(libs.annotation)


}