package pavel.ivanov.mvp_mvvm.domain.cache

import io.reactivex.rxjava3.core.Single
import pavel.ivanov.mvp_mvvm.domain.model.GithubUserModel

interface GithubUsersCacheRepository {
    fun getCacheUsers(): Single<List<GithubUserModel>>
}