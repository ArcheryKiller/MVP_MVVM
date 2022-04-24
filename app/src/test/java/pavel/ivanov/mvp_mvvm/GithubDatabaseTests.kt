package pavel.ivanov.mvp_mvvm

import org.junit.Assert
import org.junit.Test
import pavel.ivanov.mvp_mvvm.db.GithubDatabase

class GithubDatabaseTests {

    @Test
    fun githubDatabase_NotNull_ReturnTrue() {
        Assert.assertNotNull(GithubDatabase)
    }
}