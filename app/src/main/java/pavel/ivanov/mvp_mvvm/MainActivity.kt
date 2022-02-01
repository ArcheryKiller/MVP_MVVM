package pavel.ivanov.mvp_mvvm

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import pavel.ivanov.mvp_mvvm.databinding.ActivityMainBinding
import java.lang.IllegalArgumentException


class MainActivity : AppCompatActivity(), pavel.ivanov.mvp_mvvm.View {
    private lateinit var binding: ActivityMainBinding

    private val presenter = Presenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listener = View.OnClickListener {
            val current = when (it.id) {
                R.id.btnCounter1 -> ButtonType.BUTTON1
                R.id.btnCounter2 -> ButtonType.BUTTON2
                R.id.btnCounter3 -> ButtonType.BUTTON3
                else -> throw IllegalArgumentException()
            }
            presenter.counterClick(current)
        }

        binding.btnCounter1.setOnClickListener(listener)
        binding.btnCounter2.setOnClickListener(listener)
        binding.btnCounter3.setOnClickListener(listener)
    }

    override fun setButtonText(index: Int, text: String) {
        when (index) {
            0 -> binding.btnCounter1.text = text
            1 -> binding.btnCounter2.text = text
            2 -> binding.btnCounter3.text = text
        }
    }
}