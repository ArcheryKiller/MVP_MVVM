package pavel.ivanov.mvp_mvvm.domain.cache

import io.reactivex.rxjava3.core.Single
import pavel.ivanov.mvp_mvvm.domain.model.GithubRepoModel
import pavel.ivanov.mvp_mvvm.domain.model.GithubUserModel

interface GithubRepoCacheRepository {
    fun getCacheRepo(userModel: GithubUserModel): Single<List<GithubRepoModel>>
}