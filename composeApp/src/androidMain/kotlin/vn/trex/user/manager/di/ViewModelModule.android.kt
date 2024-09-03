package vn.trex.user.manager.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import vn.trex.user.manager.ui.main.MainViewModel
import vn.trex.user.manager.ui.detail.DetailViewModel

actual val viewmodelModule = module {
  viewModelOf(::MainViewModel)
  viewModelOf(::DetailViewModel)
}