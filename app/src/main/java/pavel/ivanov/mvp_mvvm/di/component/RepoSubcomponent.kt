package pavel.ivanov.mvp_mvvm.di.component

import dagger.Subcomponent
import pavel.ivanov.mvp_mvvm.di.modules.RepoModule
import pavel.ivanov.mvp_mvvm.di.scope.RepoScope
import pavel.ivanov.mvp_mvvm.ui.users.repos.ReposPresenterFactory

@Subcomponent(
    modules = [RepoModule::class]
)
@RepoScope
interface RepoSubcomponent {

    fun provideReposPresenterFactory(): ReposPresenterFactory
}