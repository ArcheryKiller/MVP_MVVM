package pavel.ivanov.mvp_mvvm.domain.users

import io.reactivex.rxjava3.core.Single
import pavel.ivanov.mvp_mvvm.model.GithubUserModel

interface IGithubUsersRepository {

    fun getUsers(): Single<List<GithubUserModel>>
}