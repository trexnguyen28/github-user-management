package vn.trex.user.manager.di

import org.koin.dsl.module
import vn.trex.user.manager.client.GithubClient
import vn.trex.user.manager.data.repository.UserRepository

val dataModule = module {
  single { UserRepository(get()) }
}