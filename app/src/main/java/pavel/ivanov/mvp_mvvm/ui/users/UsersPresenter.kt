package pavel.ivanov.mvp_mvvm.ui.users

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import pavel.ivanov.mvp_mvvm.UsersView
import pavel.ivanov.mvp_mvvm.domain.users.IGithubUsersRepository
import pavel.ivanov.mvp_mvvm.domain.model.GithubUserModel
import pavel.ivanov.mvp_mvvm.screens.AppScreens

class UsersPresenter(
    private val router: Router,
    private val usersRepository: IGithubUsersRepository,
) : MvpPresenter<UsersView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()
    }

    private fun loadData() {
        usersRepository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { users ->
                    viewState.updateList(users)
                }, {
                    viewState.showError(it.message)
                }
            )
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun onUserClicked(githubUserModel: GithubUserModel) {
        router.navigateTo(AppScreens.reposScreen(githubUserModel))
    }
}