package vn.trex.user.manager.di

import org.koin.dsl.module
import vn.trex.user.manager.client.GithubClient

val networkModule = module {
  single { GithubClient() }
}