package pavel.ivanov.mvp_mvvm

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import pavel.ivanov.mvp_mvvm.di.component.DaggerCacheComponent
import pavel.ivanov.mvp_mvvm.di.modules.ContextModule

class App : Application() {

    val cacheComponent by lazy {
        DaggerCacheComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }

    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }

    val navigationHolder
        get() = cicerone.getNavigatorHolder()

    val router
        get() = cicerone.router

    val database by lazy {
        GithubDatabase.getInstance(this)
    }

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }

    companion object {

        private var _instance: App? = null
        val instance
            get() = _instance!!
    }
}