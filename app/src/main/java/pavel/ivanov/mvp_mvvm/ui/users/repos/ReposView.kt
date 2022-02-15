package pavel.ivanov.mvp_mvvm.ui.users.repos

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import pavel.ivanov.mvp_mvvm.model.GithubRepoModel

interface ReposView : MvpView {

    @AddToEndSingle
    fun showRepos(repos: List<GithubRepoModel>?)
}