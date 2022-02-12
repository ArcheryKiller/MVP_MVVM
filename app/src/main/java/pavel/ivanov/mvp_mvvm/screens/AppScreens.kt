package pavel.ivanov.mvp_mvvm.screens

import com.github.terrakok.cicerone.androidx.FragmentScreen
import pavel.ivanov.mvp_mvvm.ui.users.UsersFragment

object AppScreens {

    fun usersScreen() = FragmentScreen {
        UsersFragment.newInstance()
    }
}