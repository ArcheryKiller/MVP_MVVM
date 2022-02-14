package pavel.ivanov.mvp_mvvm.screens

import com.github.terrakok.cicerone.androidx.FragmentScreen
import pavel.ivanov.mvp_mvvm.model.GithubUserModel
import pavel.ivanov.mvp_mvvm.ui.users.UsersFragment
import pavel.ivanov.mvp_mvvm.ui.users.repos.ReposFragment

object AppScreens {

    fun usersScreen() = FragmentScreen {
        UsersFragment.newInstance()
    }

    fun reposScreen(user: GithubUserModel) = FragmentScreen {
        ReposFragment.newInstance(user)
    }
}