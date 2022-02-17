package pavel.ivanov.mvp_mvvm

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip
import pavel.ivanov.mvp_mvvm.domain.model.GithubUserModel

interface UsersView : MvpView {

    @AddToEndSingle
    fun updateList(users: List<GithubUserModel>)

    @Skip
    fun showError(message: String?)
}
