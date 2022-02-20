package pavel.ivanov.mvp_mvvm.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import pavel.ivanov.mvp_mvvm.db.GithubDatabase
import pavel.ivanov.mvp_mvvm.db.dao.ReposDao
import pavel.ivanov.mvp_mvvm.db.dao.UserDao
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    fun db(context: Context): GithubDatabase {
        return GithubDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun usersDao(db: GithubDatabase): UserDao {
        return db.userDao
    }

    @Provides
    @Singleton
    fun reposDao(db: GithubDatabase): ReposDao {
        return db.reposDao
    }
}