package pavel.ivanov.mvp_mvvm.domain.repos

import io.reactivex.rxjava3.core.Single
import pavel.ivanov.mvp_mvvm.db.dao.ReposDao
import pavel.ivanov.mvp_mvvm.db.entity.GithubRepoEntity
import pavel.ivanov.mvp_mvvm.model.GithubRepoModel
import pavel.ivanov.mvp_mvvm.model.GithubUserModel
import pavel.ivanov.mvp_mvvm.model.Owner
import pavel.ivanov.mvp_mvvm.network.GithubApiService
import pavel.ivanov.mvp_mvvm.network.NetworkStatus
import javax.inject.Inject

class GithubReposRepository @Inject constructor(
    private val githubApiService: GithubApiService,
    private val reposDao: ReposDao,
    private val networkStatus: NetworkStatus
) : IGithubReposRepository {

    override fun getRepos(user: GithubUserModel): Single<List<GithubRepoModel>> = networkStatus.isOnlineSingle()
        .flatMap { isOnline ->
            if (isOnline) {
                githubApiService.getRepos(user.reposUrl)
                    .flatMap { repos ->
                        reposDao.insert(repos.map { GithubRepoEntity(it.id, it.name, it.owner.ownerId) })
                        Single.just(repos)
                    }
            } else {
                reposDao.getAll(user.id)
                    .map { list ->
                        list.map { repo -> GithubRepoModel(repo.name, repo.id, Owner(repo.userId)) }
                    }
            }
        }
}