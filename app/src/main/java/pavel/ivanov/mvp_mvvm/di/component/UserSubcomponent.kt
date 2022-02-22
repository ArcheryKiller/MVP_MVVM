package pavel.ivanov.mvp_mvvm.di.component

import dagger.Subcomponent
import pavel.ivanov.mvp_mvvm.di.modules.UserModule
import pavel.ivanov.mvp_mvvm.di.scope.UserScope
import pavel.ivanov.mvp_mvvm.ui.users.UsersPresenter

@Subcomponent(
    modules = [
        UserModule::class,
    ]
)
@UserScope
interface UserSubcomponent {

    fun repoSubcomponent(): RepoSubcomponent

    fun provideUsersPresenter(): UsersPresenter
}