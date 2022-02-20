package pavel.ivanov.mvp_mvvm.di.component

import dagger.Component
import pavel.ivanov.mvp_mvvm.di.modules.CacheModules
import pavel.ivanov.mvp_mvvm.di.modules.ContextModule
import pavel.ivanov.mvp_mvvm.di.modules.StatusModule
import pavel.ivanov.mvp_mvvm.domain.cache.RoomGithubRepositoriesCache
import pavel.ivanov.mvp_mvvm.domain.cache.RoomGithubUsersCache
import javax.inject.Singleton

@Component(
    modules = [
        CacheModules::class,
        ContextModule::class,
        StatusModule::class
    ]
)

@Singleton
interface CacheComponent {

    fun inject(roomGithubUsersCache: RoomGithubUsersCache)

    fun inject(roomGithubRepositoriesCache: RoomGithubRepositoriesCache)
}