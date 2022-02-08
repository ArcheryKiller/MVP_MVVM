package pavel.ivanov.mvp_mvvm

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import pavel.ivanov.mvp_mvvm.rxjava.RxJavaTest

class App : Application() {
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router
    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    private val rxJavaTest = RxJavaTest()

    override fun onCreate() {
        super.onCreate()
        instance = this
        rxJavaTest.observableCreate()
    }

    companion object {
        lateinit var instance: App
    }
}