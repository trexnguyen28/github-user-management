package vn.trex.user.manager.di

import org.koin.core.module.dsl.singleOf

import org.koin.dsl.module
import vn.trex.user.manager.ui.main.MainViewModel

actual val viewmodelModule = module {
  singleOf(::MainViewModel)
}