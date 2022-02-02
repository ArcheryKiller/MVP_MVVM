package pavel.ivanov.mvp_mvvm

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import pavel.ivanov.mvp_mvvm.databinding.FragmentUsersBinding

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(GithubUserRepo(), App.instance.router)
    }
    var adapter: UsersRVAdapter? = null
    private var binding: FragmentUsersBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUsersBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun init() {
        binding.run {
            this?.rvUsers?.layoutManager = LinearLayoutManager(context)
            adapter = UsersRVAdapter(presenter.usersListPresenter)
            this?.rvUsers?.adapter = adapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()

    companion object {
        fun newInstance() = UsersFragment()
    }
}

