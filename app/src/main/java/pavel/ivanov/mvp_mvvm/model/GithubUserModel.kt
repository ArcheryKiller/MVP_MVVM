package pavel.ivanov.mvp_mvvm.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUserModel(
    @Expose
    val id: Long,

    @Expose
    val login: String,

    @Expose
    val avatarUrl: String? = null,

    @Expose
    val reposUrl: String,
) : Parcelable

