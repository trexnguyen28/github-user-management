package vn.trex.user.manager.di

import org.koin.dsl.module
import vn.trex.user.manager.data.UserCachingStore
import vn.trex.user.manager.data.dataStorePreferences
import vn.trex.user.manager.data.repository.UserRepository

val dataModule = module {
  single {
    UserCachingStore(dataStorePreferences())
  }
  single { UserRepository(get(), get()) }
}