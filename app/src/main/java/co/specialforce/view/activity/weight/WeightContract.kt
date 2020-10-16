package co.specialforce.view.activity.weight

import co.specialforce.base.BaseContract

interface WeightContract {
    interface View: BaseContract.BaseView<Presenter>{ }

    interface Presenter : BaseContract.BasePresenter { }
}