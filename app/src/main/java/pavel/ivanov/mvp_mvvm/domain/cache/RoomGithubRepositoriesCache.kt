package pavel.ivanov.mvp_mvvm.domain.cache

import io.reactivex.rxjava3.core.Single
import pavel.ivanov.mvp_mvvm.db.AppDatabase
import pavel.ivanov.mvp_mvvm.db.entity.GithubRepoEntity
import pavel.ivanov.mvp_mvvm.domain.model.GithubRepoModel
import pavel.ivanov.mvp_mvvm.domain.model.GithubUserModel
import pavel.ivanov.mvp_mvvm.network.ApiHolder
import pavel.ivanov.mvp_mvvm.network.GithubApiService
import pavel.ivanov.mvp_mvvm.network.NetworkStatus

class RoomGithubRepositoriesCache(
    private val networkStatus: NetworkStatus
): GithubRepoCacheRepository {
    private val retrofitService: GithubApiService = ApiHolder.githubApiService
    private val db: AppDatabase = AppDatabase.instance

    override fun getCacheRepo(userModel: GithubUserModel): Single<List<GithubRepoModel>> {
        return if (networkStatus.isOnline()) {
            retrofitService.getRepos(userModel.reposUrl)
                .flatMap { repos ->
                    Single.fromCallable {
                        val dbRepos = repos.map {
                            GithubRepoEntity(it.id, it.name, it.owner.ownerId.toString())
                        }
                        db.repositoryDao.insert(dbRepos)
                        repos
                    }
                }
        } else {
            Single.fromCallable {
                db.repositoryDao.getByUserId(userModel.id)
                    .map { repo -> GithubRepoModel( userModel.login, userModel.id, userModel.reposUrl) }
            }
        }
    }
}