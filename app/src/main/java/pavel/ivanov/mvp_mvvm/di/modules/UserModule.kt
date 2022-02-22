package pavel.ivanov.mvp_mvvm.di.modules

import dagger.Module
import dagger.Provides
import pavel.ivanov.mvp_mvvm.db.dao.UserDao
import pavel.ivanov.mvp_mvvm.di.scope.UserScope
import pavel.ivanov.mvp_mvvm.domain.users.GithubUsersRepository
import pavel.ivanov.mvp_mvvm.domain.users.IGithubUsersRepository
import pavel.ivanov.mvp_mvvm.network.GithubApiService
import pavel.ivanov.mvp_mvvm.network.NetworkStatus

@Module
class UserModule {

    @Provides
    @UserScope
    fun provideUserRepository(
        apiService: GithubApiService,
        userDao: UserDao,
        networkStatus: NetworkStatus
    ): IGithubUsersRepository {
        return GithubUsersRepository(apiService, userDao, networkStatus)
    }
}