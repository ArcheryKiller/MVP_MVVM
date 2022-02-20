package pavel.ivanov.mvp_mvvm

import android.app.Application
import pavel.ivanov.mvp_mvvm.di.modules.ContextModule
import pavel.ivanov.mvp_mvvm.di.component.DaggerAppComponent


class App : Application() {

    val appComponent by lazy {

        DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
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