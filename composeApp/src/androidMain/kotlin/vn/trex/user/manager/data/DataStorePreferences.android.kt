package vn.trex.user.manager.data

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.koin.mp.KoinPlatform
import vn.trex.user.manager.utils.dataStoreFileName

actual fun dataStorePreferences(): DataStore<Preferences> {
  val appContext = KoinPlatform.getKoin().get<Application>()
  return AppCaching.getDataStore(
    producePath = {
      appContext.filesDir
        .resolve(dataStoreFileName)
        .absolutePath
    }
  )
}