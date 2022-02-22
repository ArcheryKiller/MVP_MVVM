package pavel.ivanov.mvp_mvvm.ui.users.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import pavel.ivanov.mvp_mvvm.App
import pavel.ivanov.mvp_mvvm.databinding.FragmentReposBinding
import pavel.ivanov.mvp_mvvm.model.GithubRepoModel
import pavel.ivanov.mvp_mvvm.model.GithubUserModel
import pavel.ivanov.mvp_mvvm.ui.base.BackButtonListener
import pavel.ivanov.mvp_mvvm.ui.users.repos.adapter.ReposAdapter

class ReposFragment : MvpAppCompatFragment(), ReposView, BackButtonListener {

    private val userModel by lazy {
        requireArguments().getParcelable<GithubUserModel>(KEY_USER_MODEL)!!
    }

    private var _binding: FragmentReposBinding? = null
    private val binding: FragmentReposBinding
        get() = _binding!!

    private val presenter by moxyPresenter {
        App.instance.initRepoSubcomponent()
        App.instance.reposSubcomponent?.provideReposPresenterFactory()?.presenter(userModel)!!
    }

    private val adapter by lazy {
        ReposAdapter(presenter::onItemClicked)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentReposBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.reposRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.reposRecycler.adapter = adapter
    }

    override fun showRepos(repos: List<GithubRepoModel>?) {
        adapter.submitList(repos)
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        private const val KEY_USER_MODEL = "KEY_USER_MODEL"

        fun newInstance(user: GithubUserModel): ReposFragment {
            return ReposFragment().apply {
                arguments = bundleOf(KEY_USER_MODEL to user)
            }
        }
    }
}