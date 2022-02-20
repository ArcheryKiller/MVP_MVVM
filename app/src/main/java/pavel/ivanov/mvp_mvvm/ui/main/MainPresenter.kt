package pavel.ivanov.mvp_mvvm.ui.main

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import pavel.ivanov.mvp_mvvm.screens.AppScreens
import pavel.ivanov.mvp_mvvm.screens.IScreens
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val router: Router,
    private val screens: IScreens,
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.replaceScreen(screens.usersScreen())
    }

    fun backPressed() {
        router.exit()
    }
}