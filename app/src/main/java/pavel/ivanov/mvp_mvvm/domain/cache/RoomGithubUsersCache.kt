package pavel.ivanov.mvp_mvvm.domain.cache

import io.reactivex.rxjava3.core.Single
import pavel.ivanov.mvp_mvvm.db.AppDatabase
import pavel.ivanov.mvp_mvvm.db.entity.GithubUserEntity
import pavel.ivanov.mvp_mvvm.domain.model.GithubUserModel
import pavel.ivanov.mvp_mvvm.network.ApiHolder
import pavel.ivanov.mvp_mvvm.network.GithubApiService
import pavel.ivanov.mvp_mvvm.network.NetworkStatus

class RoomGithubUsersCache(
    private val networkStatus: NetworkStatus,
): GithubUsersCacheRepository {
    private val retrofitService: GithubApiService = ApiHolder.githubApiService
    private val db: AppDatabase = AppDatabase.instance

    override fun getCacheUsers(): Single<List<GithubUserModel>> {
        return if (networkStatus.isOnline()) {
            retrofitService.getUsers()
                .flatMap { users ->
                    Single.fromCallable {
                        val roomUsers = users.map { user ->
                            GithubUserEntity(user.id, user.login, user.reposUrl, user.reposUrl)
                        }
                        db.userDao.insert(roomUsers)
                        users
                    }
                }
        } else {
            Single.fromCallable {
                db.userDao.getAll().map { roomModel ->
                    GithubUserModel(
                        roomModel.id, roomModel.login, roomModel.avatarUrl, roomModel.reposUrl)
                }
            }
        }
    }
}