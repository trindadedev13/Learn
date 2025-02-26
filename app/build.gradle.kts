plugins {
  id("com.android.application")
  id("kotlin-android")
}

android {
  namespace = "dev.trindadedev.learn"
  compileSdk = 34
    
  defaultConfig {
    applicationId = "dev.trindadedev.learn"
    minSdk = 21
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"
        
    vectorDrawables.useSupportLibrary = true
  }
    
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
  }

  signingConfigs {
    create("release") {
      // temporary keystore
      storeFile = file(layout.buildDirectory.dir("../release_key.jks"))
      storePassword = "release_temp"
      keyAlias = "release_temp"
      keyPassword = "release_temp"
    }
    getByName("debug") {
      storeFile = file(layout.buildDirectory.dir("../testkey.keystore"))
      storePassword = "testkey"
      keyAlias = "testkey"
      keyPassword = "testkey"
    }
  }
    
  buildTypes {
    release {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
      signingConfig = signingConfigs.getByName("release")
    }
  }
  
  buildFeatures {
    viewBinding = true
  }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
  kotlinOptions.jvmTarget = "21"
}

dependencies {
  implementation("androidx.constraintlayout:constraintlayout:2.2.1")
  implementation("com.google.android.material:material:1.12.0")
  implementation("androidx.appcompat:appcompat:1.7.0")
  implementation("androidx.activity:activity:1.10.0")
}