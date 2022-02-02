package pavel.ivanov.mvp_mvvm

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : Screens {
    override fun users(): Screen {
        return FragmentScreen { UsersFragment.newInstance() }
    }
}