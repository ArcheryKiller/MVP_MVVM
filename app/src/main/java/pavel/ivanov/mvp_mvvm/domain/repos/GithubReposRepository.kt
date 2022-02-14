package pavel.ivanov.mvp_mvvm.domain.repos

import io.reactivex.rxjava3.core.Single
import pavel.ivanov.mvp_mvvm.model.GithubRepoModel
import pavel.ivanov.mvp_mvvm.network.GithubApiService

class GithubReposRepository(private val githubApiService: GithubApiService) :
    IGithubReposRepository {

    override fun getRepos(reposUrl: String): Single<List<GithubRepoModel>> {
        return githubApiService.getRepos(reposUrl)
    }
}