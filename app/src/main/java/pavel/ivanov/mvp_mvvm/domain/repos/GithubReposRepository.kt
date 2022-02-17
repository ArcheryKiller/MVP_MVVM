package pavel.ivanov.mvp_mvvm.domain.repos

import io.reactivex.rxjava3.core.Single
import pavel.ivanov.mvp_mvvm.db.dao.RepoDao
import pavel.ivanov.mvp_mvvm.db.entity.GithubRepoEntity
import pavel.ivanov.mvp_mvvm.domain.model.GithubRepoModel
import pavel.ivanov.mvp_mvvm.domain.model.GithubUserModel
import pavel.ivanov.mvp_mvvm.domain.model.Owner
import pavel.ivanov.mvp_mvvm.network.GithubApiService
import pavel.ivanov.mvp_mvvm.network.NetworkStatus

class GithubReposRepository(
    private val githubApiService: GithubApiService,
    private val repoDao: RepoDao,
    private val networkStatus: NetworkStatus,
) : IGithubReposRepository {

    override fun getRepos(user: GithubUserModel): Single<List<GithubRepoModel>> = networkStatus.isOnlineSingle()
        .flatMap {isOnline ->
            if (isOnline) {
                githubApiService.getRepos(user.reposUrl)
                    .flatMap {  repos ->
                        repoDao.insert(repos.map { GithubRepoEntity(it.id, it.name, it.owner.ownerId) })
                        Single.just(repos)
                    }
            } else {
                repoDao.getAll(user.id)
                    .map { list ->
                        list.map { repo -> GithubRepoModel(repo.name, repo.id, Owner(repo.userId)) }
                    }
            }
        }
}