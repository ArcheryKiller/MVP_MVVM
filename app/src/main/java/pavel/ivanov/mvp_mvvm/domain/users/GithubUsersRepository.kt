package pavel.ivanov.mvp_mvvm.domain.users

import io.reactivex.rxjava3.core.Single
import pavel.ivanov.mvp_mvvm.db.dao.UserDao
import pavel.ivanov.mvp_mvvm.db.entity.GithubUserEntity
import pavel.ivanov.mvp_mvvm.domain.cache.GithubUsersCacheRepository
import pavel.ivanov.mvp_mvvm.domain.model.GithubUserModel
import pavel.ivanov.mvp_mvvm.network.GithubApiService
import pavel.ivanov.mvp_mvvm.network.NetworkStatus

class GithubUsersRepository(
    private val RoomGithubUsersCache: GithubUsersCacheRepository
): IGithubUsersRepository {

    override fun getUsers(): Single<List<GithubUserModel>> {
        return RoomGithubUsersCache.getCacheUsers()
    }
}