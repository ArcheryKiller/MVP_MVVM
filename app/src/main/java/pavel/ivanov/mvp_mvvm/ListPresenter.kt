package pavel.ivanov.mvp_mvvm

interface ListPresenter<V : ItemView> {
    var itemClickListener: ((V) -> Unit)?

    fun bindingView(view: V)

    fun getCount(): Int
}