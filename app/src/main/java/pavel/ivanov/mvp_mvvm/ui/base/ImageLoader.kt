package pavel.ivanov.mvp_mvvm.ui.base

interface ImageLoader<T> {

    fun loadInto(url: String, container: T)
}