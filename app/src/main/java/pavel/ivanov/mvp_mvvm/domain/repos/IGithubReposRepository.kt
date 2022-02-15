package pavel.ivanov.mvp_mvvm.domain.repos

import io.reactivex.rxjava3.core.Single
import pavel.ivanov.mvp_mvvm.model.GithubRepoModel

interface IGithubReposRepository {

    fun getRepos(reposUrl: String): Single<List<GithubRepoModel>>
}