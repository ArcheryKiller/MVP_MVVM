package pavel.ivanov.mvp_mvvm.domain.users

import io.reactivex.rxjava3.core.Single
import pavel.ivanov.mvp_mvvm.db.dao.UserDao
import pavel.ivanov.mvp_mvvm.db.entity.GithubUserEntity
import pavel.ivanov.mvp_mvvm.domain.model.GithubUserModel
import pavel.ivanov.mvp_mvvm.network.GithubApiService
import pavel.ivanov.mvp_mvvm.network.NetworkStatus

class GithubUsersRepository(
    private val githubApiService: GithubApiService,
    private val userDao: UserDao,
    private val networkStatus: NetworkStatus,
) : IGithubUsersRepository {

    override fun getUsers(): Single<List<GithubUserModel>> = networkStatus.isOnlineSingle()
        .flatMap {isOnline ->
            if (isOnline) {
                githubApiService.getUsers()
                    .flatMap {  users ->
                        userDao.insert(users.map { GithubUserEntity(it.id, it.login, it.avatarUrl ?: "", it.reposUrl) })
                        Single.just(users)
                    }
            } else {
                userDao.getAll()
                    .map { list ->
                        list.map { user -> GithubUserModel(user.id, user.login, user.avatarUrl, user.reposUrl) }
                    }
            }
        }
}