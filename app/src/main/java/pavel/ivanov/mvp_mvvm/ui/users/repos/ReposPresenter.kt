package pavel.ivanov.mvp_mvvm.ui.users.repos

import android.util.Log
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import pavel.ivanov.mvp_mvvm.App
import pavel.ivanov.mvp_mvvm.domain.repos.IGithubReposRepository
import pavel.ivanov.mvp_mvvm.model.GithubRepoModel
import pavel.ivanov.mvp_mvvm.model.GithubUserModel

class ReposPresenter @AssistedInject constructor(
    @Assisted private val userModel: GithubUserModel,
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

    override fun onDestroy() {
        super.onDestroy()
        App.instance.destroyRepoScope()
    }

    fun onItemClicked(repo: GithubRepoModel) {
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}

@AssistedFactory
interface ReposPresenterFactory {

    fun presenter(userModel: GithubUserModel): ReposPresenter
}