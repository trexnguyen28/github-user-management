import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.androidApplication)
  alias(libs.plugins.jetbrainsCompose)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.kotlin.serialization)
}

kotlin {
  androidTarget {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
      jvmTarget.set(JvmTarget.JVM_11)
    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    instrumentedTestVariant {
      sourceSetTree.set(KotlinSourceSetTree.test)

      dependencies {
        implementation(libs.core.ktx)
//        implementation(libs.compose.ui.test.junit4.android)
        debugImplementation(libs.compose.ui.test.manifest)
      }
    }
  }

  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64()
  ).forEach { iosTarget ->
    iosTarget.binaries.framework {
      baseName = "ComposeApp"
      isStatic = true
    }
  }

  sourceSets {
    androidMain.dependencies {
      implementation(compose.preview)
      implementation(libs.androidx.activity.compose)

      // Ktor
      implementation(libs.ktor.client.okhttp)

      // Koin
      implementation(libs.koin.android)
      implementation(libs.koin.androidx.compose)
    }

    commonMain.dependencies {
      implementation(compose.runtime)
      implementation(compose.foundation)
      implementation(compose.material)
      implementation(compose.ui)
      implementation(compose.components.resources)
      implementation(compose.components.uiToolingPreview)

      // Ktor
      implementation(libs.bundles.ktor)

      // Coil
      implementation(libs.coil.compose.core)
      implementation(libs.coil.compose)
      implementation(libs.coil.mp)
      implementation(libs.coil.network.ktor)

      // Voyager
      implementation(libs.voyager.navigator)
      implementation(libs.navigation.compose)

      // Koin
      api(libs.koin.core)
      implementation(libs.koin.compose)
      implementation(libs.lifecycle.viewmodel)

      // Data Store
      implementation(libs.androidx.data.store.core)

      // Logger
      implementation(libs.kermit)
    }

    nativeMain.dependencies {
      implementation(libs.ktor.client.darwin)
    }

    commonTest.dependencies {
      implementation(libs.kotlin.test)
      implementation(kotlin("test-annotations-common"))
      implementation(libs.assertk)

      @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
      implementation(compose.uiTest)
    }
  }
}

android {
  namespace = "vn.trex.user.manager"
  compileSdk = libs.versions.android.compileSdk.get().toInt()

  sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
  sourceSets["main"].res.srcDirs("src/androidMain/res")
  sourceSets["main"].resources.srcDirs("src/commonMain/resources")

  defaultConfig {
    applicationId = "vn.trex.user.manager"
    minSdk = libs.versions.android.minSdk.get().toInt()
    targetSdk = libs.versions.android.targetSdk.get().toInt()
    versionCode = 1
    versionName = "1.0"
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  buildFeatures {
    compose = true
  }
  dependencies {
    debugImplementation(compose.uiTooling)
  }
}

