package pavel.ivanov.mvp_mvvm.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Single
import pavel.ivanov.mvp_mvvm.db.entity.GithubRepoEntity

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: GithubRepoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<GithubRepoEntity>)

    @Query("SELECT * FROM GithubRepoEntity")
    fun getAll(): List<GithubRepoEntity>

    @Query("SELECT * FROM GithubRepoEntity WHERE id = :userId")
    fun getByUserId(userId: Long): List<GithubRepoEntity>
}