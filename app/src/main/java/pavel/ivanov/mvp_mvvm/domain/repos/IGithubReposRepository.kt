package pavel.ivanov.mvp_mvvm.domain.repos

import io.reactivex.rxjava3.core.Single
import pavel.ivanov.mvp_mvvm.model.GithubRepoModel
import pavel.ivanov.mvp_mvvm.model.GithubUserModel

interface IGithubReposRepository {

    fun getRepos(user: GithubUserModel): Single<List<GithubRepoModel>>
}