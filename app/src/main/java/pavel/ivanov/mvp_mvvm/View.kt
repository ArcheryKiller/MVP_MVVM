package pavel.ivanov.mvp_mvvm

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface View : MvpView {}

@StateStrategyType(AddToEndSingleStrategy::class)
interface UsersView : MvpView {
    fun init()
    fun updateList()
}
