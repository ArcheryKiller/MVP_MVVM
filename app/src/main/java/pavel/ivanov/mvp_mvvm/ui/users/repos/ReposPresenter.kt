package pavel.ivanov.mvp_mvvm.ui.users.repos

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import pavel.ivanov.mvp_mvvm.domain.repos.IGithubReposRepository
import pavel.ivanov.mvp_mvvm.domain.model.GithubRepoModel
import pavel.ivanov.mvp_mvvm.domain.model.GithubUserModel

class ReposPresenter(
    private val userModel: GithubUserModel,
    private val reposRepository: IGithubReposRepository,
    private val router: Router,
) : MvpPresenter<ReposView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        reposRepository.getRepos(userModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { repos ->
                    viewState.showRepos(repos)
                },
                {
                    Log.e("Repos", "Error getting repos", it)
                }
            )
    }

    fun onItemClicked(repo: GithubRepoModel) {

    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}