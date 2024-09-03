package vn.trex.user.manager

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import vn.trex.user.manager.di.initKoin

class MyApp : Application(), KoinComponent {
  override fun onCreate() {
    super.onCreate()
    // Initialize Koin
    initKoin {
      androidLogger()
      androidContext(this@MyApp)
    }
  }
}