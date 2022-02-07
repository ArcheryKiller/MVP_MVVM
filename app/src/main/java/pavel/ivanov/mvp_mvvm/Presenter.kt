package pavel.ivanov.mvp_mvvm

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class Presenter(val router: Router, val screen: Screens) : MvpPresenter<View>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screen.users())
    }

    fun backClicked() {
        router.exit()
    }
}

