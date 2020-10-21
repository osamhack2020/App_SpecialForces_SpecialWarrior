package co.specialforce.view.activity.weight

import co.specialforce.base.BaseContract

interface WeightContract {
    interface Model{
        fun weightInput(weight: Float)
    }
    interface View: BaseContract.BaseView<Presenter>{ }

    interface Presenter : BaseContract.BasePresenter {
        fun weightInput(weight: Float)
    }
}