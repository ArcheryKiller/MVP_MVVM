package pavel.ivanov.mvp_mvvm.ui.users

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import pavel.ivanov.mvp_mvvm.App
import pavel.ivanov.mvp_mvvm.domain.users.IGithubUsersRepository
import pavel.ivanov.mvp_mvvm.model.GithubUserModel
import pavel.ivanov.mvp_mvvm.screens.IScreens
import javax.inject.Inject

class UsersPresenter @Inject constructor(
    private val usersRepository: IGithubUsersRepository,
    private val router: Router,
    private val screens: IScreens,
) : MvpPresenter<UsersView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        App.instance.destroyUserScope()
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
        router.navigateTo(screens.reposScreen(githubUserModel))
    }
}