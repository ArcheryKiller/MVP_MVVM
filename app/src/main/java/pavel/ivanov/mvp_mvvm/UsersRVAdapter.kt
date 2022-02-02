package pavel.ivanov.mvp_mvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import pavel.ivanov.mvp_mvvm.databinding.UserBinding


class UsersRVAdapter(val presenter: UserListPresenter) :
    Adapter<UsersRVAdapter.ViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        UserBinding.inflate(
        LayoutInflater.from(parent.context),
            parent,
            false)).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        presenter.bindingView(holder.apply { posit = pos })
    }

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(val binding: UserBinding) :
        RecyclerView.ViewHolder(binding.root), UserItemView {

        override var posit = -1

        override fun setLogin(text: String) = with(binding) {
            tvLogin.text = text
        }
    }
}