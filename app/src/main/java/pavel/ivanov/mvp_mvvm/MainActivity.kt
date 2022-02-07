package pavel.ivanov.mvp_mvvm

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import pavel.ivanov.mvp_mvvm.databinding.ActivityMainBinding


class MainActivity : MvpAppCompatActivity(), View {
    private lateinit var binding: ActivityMainBinding
    private val presenter by moxyPresenter { Presenter(App.instance.router, AndroidScreens()) }
    val navigator = AppNavigator(this, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed())
                return
        }
        presenter.backClicked()
    }
}