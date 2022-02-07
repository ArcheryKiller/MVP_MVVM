package pavel.ivanov.mvp_mvvm

class GithubUserRepo {
    private val repositories =
        (0..100).map { GithubUser("Login $it") }

    fun getUsers(): List<GithubUser> {
        return repositories
    }
}