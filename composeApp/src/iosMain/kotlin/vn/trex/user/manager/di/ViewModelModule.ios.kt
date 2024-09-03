package vn.trex.user.manager.di

import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.factoryOf

import org.koin.dsl.module
import vn.trex.user.manager.ui.main.MainViewModel
import vn.trex.user.manager.ui.detail.DetailViewModel

actual val viewmodelModule = module {
  singleOf(::MainViewModel)
  factoryOf(::DetailViewModel)
}