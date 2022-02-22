package pavel.ivanov.mvp_mvvm.di.component

import dagger.Component
import pavel.ivanov.mvp_mvvm.di.modules.ContextModule
import pavel.ivanov.mvp_mvvm.di.modules.DbModule
import pavel.ivanov.mvp_mvvm.di.modules.NavigationModule
import pavel.ivanov.mvp_mvvm.di.modules.NetworkModule
import pavel.ivanov.mvp_mvvm.di.modules.RepositoryModule
import pavel.ivanov.mvp_mvvm.ui.main.MainActivity
import pavel.ivanov.mvp_mvvm.ui.main.MainPresenter
import pavel.ivanov.mvp_mvvm.ui.users.repos.ReposPresenter
import pavel.ivanov.mvp_mvvm.ui.users.repos.ReposPresenterFactory
import pavel.ivanov.mvp_mvvm.ui.users.UsersPresenter
import javax.inject.Singleton

@Component(
    modules = [
        ContextModule::class,
        NavigationModule::class,
        DbModule::class,
        NetworkModule::class,
    ]
)
@Singleton
interface AppComponent {

    fun userSubcomponent(): UserSubcomponent

    fun provideMainPresenter(): MainPresenter

    fun inject(mainActivity: MainActivity)

    fun inject(mainPresenter: MainPresenter)
}