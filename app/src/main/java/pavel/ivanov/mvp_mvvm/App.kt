package pavel.ivanov.mvp_mvvm

import android.app.Application
import pavel.ivanov.mvp_mvvm.di.modules.ContextModule
import pavel.ivanov.mvp_mvvm.di.component.DaggerAppComponent
import pavel.ivanov.mvp_mvvm.di.component.RepoSubcomponent
import pavel.ivanov.mvp_mvvm.di.component.UserSubcomponent


class App : Application() {

    val appComponent by lazy {
        DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }

    var usersSubcomponent: UserSubcomponent? = null
        private set
    var reposSubcomponent: RepoSubcomponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }

    fun initUserSubcomponent(): UserSubcomponent {
        usersSubcomponent = appComponent.userSubcomponent()
        return usersSubcomponent!!
    }

    fun initRepoSubcomponent(): RepoSubcomponent {
        reposSubcomponent = appComponent.userSubcomponent().repoSubcomponent()
        return reposSubcomponent!!
    }

    fun destroyRepoScope() {
        reposSubcomponent = null
    }

    fun destroyUserScope() {
        usersSubcomponent = null
    }

    companion object {

        private var _instance: App? = null
        val instance
            get() = _instance!!
    }
}