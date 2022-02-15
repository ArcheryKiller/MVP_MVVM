package pavel.ivanov.mvp_mvvm.network

import io.reactivex.rxjava3.core.Single
import pavel.ivanov.mvp_mvvm.model.GithubRepoModel
import pavel.ivanov.mvp_mvvm.model.GithubUserModel
import retrofit2.http.GET
import retrofit2.http.Url

interface GithubApiService {

    @GET("/users")
    fun getUsers(): Single<List<GithubUserModel>>

    @GET()
    fun getRepos(@Url reposUrl: String): Single<List<GithubRepoModel>>
}