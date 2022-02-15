package pavel.ivanov.mvp_mvvm.ui.main

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import pavel.ivanov.mvp_mvvm.screens.AppScreens

class MainPresenter(
    private val router: Router,
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.replaceScreen(AppScreens.usersScreen())
    }

    fun backPressed() {
        router.exit()
    }
}