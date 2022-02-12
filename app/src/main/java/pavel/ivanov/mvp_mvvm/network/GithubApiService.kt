package pavel.ivanov.mvp_mvvm.network

import io.reactivex.rxjava3.core.Single
import pavel.ivanov.mvp_mvvm.model.GithubUserModel
import retrofit2.http.GET

interface GithubApiService {

    @GET("/users")
    fun getUsers(): Single<List<GithubUserModel>>
}