package pavel.ivanov.mvp_mvvm.di.modules

import dagger.Binds
import dagger.Module
import pavel.ivanov.mvp_mvvm.domain.repos.GithubReposRepository
import pavel.ivanov.mvp_mvvm.domain.repos.IGithubReposRepository
import pavel.ivanov.mvp_mvvm.domain.users.GithubUsersRepository
import pavel.ivanov.mvp_mvvm.domain.users.IGithubUsersRepository
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun provideUserRepository(impl: GithubUsersRepository): IGithubUsersRepository

    @Binds
    @Singleton
    fun provideRepoRepository(impl: GithubReposRepository): IGithubReposRepository
}