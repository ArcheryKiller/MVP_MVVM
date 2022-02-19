package pavel.ivanov.mvp_mvvm.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Single
import pavel.ivanov.mvp_mvvm.db.entity.GithubUserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: List<GithubUserEntity>)

    @Query("SELECT * FROM GithubUserEntity")
    fun getAll(): Single<List<GithubUserEntity>>
}