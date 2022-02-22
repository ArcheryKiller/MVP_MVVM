package pavel.ivanov.mvp_mvvm.di.modules

import dagger.Module
import dagger.Provides
import pavel.ivanov.mvp_mvvm.db.dao.ReposDao
import pavel.ivanov.mvp_mvvm.di.scope.RepoScope
import pavel.ivanov.mvp_mvvm.domain.repos.GithubReposRepository
import pavel.ivanov.mvp_mvvm.domain.repos.IGithubReposRepository
import pavel.ivanov.mvp_mvvm.network.GithubApiService
import pavel.ivanov.mvp_mvvm.network.NetworkStatus

@Module
class RepoModule {

    @Provides
    @RepoScope
    fun provideRepoRepository(
        apiService: GithubApiService,
        reposDao: ReposDao,
        networkStatus: NetworkStatus
    ): IGithubReposRepository {
        return GithubReposRepository(apiService, reposDao, networkStatus)
    }
}