package pavel.ivanov.mvp_mvvm.di.modules

import dagger.Module
import dagger.Provides
import pavel.ivanov.mvp_mvvm.domain.cache.GithubRepoCacheRepository
import pavel.ivanov.mvp_mvvm.domain.cache.GithubUsersCacheRepository
import pavel.ivanov.mvp_mvvm.domain.cache.RoomGithubRepositoriesCache
import pavel.ivanov.mvp_mvvm.domain.cache.RoomGithubUsersCache
import pavel.ivanov.mvp_mvvm.network.NetworkStatus
import javax.inject.Singleton

@Module
class CacheModules {

    @Provides
    @Singleton
    fun usersCacheRepo(networkStatus: NetworkStatus): GithubUsersCacheRepository{
        return RoomGithubUsersCache(networkStatus)
    }

    @Provides
    @Singleton
    fun reposCacheRepo(networkStatus: NetworkStatus): GithubRepoCacheRepository {
        return RoomGithubRepositoriesCache(networkStatus)
    }
}