package pavel.ivanov.mvp_mvvm.domain.repos

import io.reactivex.rxjava3.core.Single
import pavel.ivanov.mvp_mvvm.domain.cache.GithubRepoCacheRepository
import pavel.ivanov.mvp_mvvm.domain.model.GithubRepoModel
import pavel.ivanov.mvp_mvvm.domain.model.GithubUserModel

class GithubReposRepository(
    private val roomGithubRepositoriesCache: GithubRepoCacheRepository
): IGithubReposRepository {

    override fun getRepos(userModel: GithubUserModel): Single<List<GithubRepoModel>> {
        return roomGithubRepositoriesCache.getCacheRepo(userModel)
    }
}