package pavel.ivanov.mvp_mvvm.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pavel.ivanov.mvp_mvvm.App
import pavel.ivanov.mvp_mvvm.db.dao.RepoDao
import pavel.ivanov.mvp_mvvm.db.dao.UserDao
import pavel.ivanov.mvp_mvvm.db.entity.GithubRepoEntity
import pavel.ivanov.mvp_mvvm.db.entity.GithubUserEntity

@Database(
    entities = [GithubUserEntity::class, GithubRepoEntity::class],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {

    abstract val userDao: UserDao

    abstract val repositoryDao: RepoDao

    companion object {
        private const val DB_NAME = "database.db"

        val instance by lazy {
            Room.databaseBuilder(App.instance, AppDatabase::class.java, DB_NAME)
                .build()
        }
    }
}