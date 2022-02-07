package pavel.ivanov.mvp_mvvm

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UsersPresenter(val usersRepo: GithubUserRepo, val router: Router) : MvpPresenter<UsersView>() {
    val usersListPresenter = UserListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = {itemView ->
            //переход на экран пользователя
        }
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    class UserListPresenter : pavel.ivanov.mvp_mvvm.UserListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindingView(view: UserItemView) {
            val user = users[view.posit]
            view.setLogin(user.login)
        }

        override fun getCount() = users.size
    }
}