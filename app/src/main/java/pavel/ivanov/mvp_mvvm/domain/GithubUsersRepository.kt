package pavel.ivanov.mvp_mvvm.domain

import io.reactivex.rxjava3.core.Single
import pavel.ivanov.mvp_mvvm.model.GithubUserModel
import pavel.ivanov.mvp_mvvm.network.GithubApiService

class GithubUsersRepository(private val githubApiService: GithubApiService) : IGithubUsersRepository {

    override fun getUsers(): Single<List<GithubUserModel>> {
        return githubApiService.getUsers()
    }
}