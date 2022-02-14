package pavel.ivanov.mvp_mvvm.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import pavel.ivanov.mvp_mvvm.*
import pavel.ivanov.mvp_mvvm.databinding.FragmentUsersBinding
import pavel.ivanov.mvp_mvvm.domain.users.GithubUsersRepository
import pavel.ivanov.mvp_mvvm.model.GithubUserModel
import pavel.ivanov.mvp_mvvm.network.ApiHolder
import pavel.ivanov.mvp_mvvm.ui.base.BackButtonListener
import pavel.ivanov.mvp_mvvm.ui.base.GlideImageLoader
import pavel.ivanov.mvp_mvvm.ui.users.adapter.UsersAdapter

class UsersFragment() : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private val presenter by moxyPresenter {
        UsersPresenter(
            App.instance.router,
            GithubUsersRepository(ApiHolder.githubApiService)
        )
    }

    private var _binding: FragmentUsersBinding? = null
    private val binding
        get() = _binding!!

    private val adapter by lazy {
        UsersAdapter(GlideImageLoader(), presenter::onUserClicked)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.usersRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.usersRecycler.adapter = adapter
    }

    override fun updateList(users: List<GithubUserModel>) {
        adapter.submitList(users)
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }

    override fun showError(message: String?) {
        Toast.makeText(requireContext(), message.orEmpty(), Toast.LENGTH_SHORT)
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        private const val KEY_INIT_PARAMS = "KEY_INIT_PARAMS"

        fun newInstance(): UsersFragment {
            return UsersFragment()
        }
    }
}
