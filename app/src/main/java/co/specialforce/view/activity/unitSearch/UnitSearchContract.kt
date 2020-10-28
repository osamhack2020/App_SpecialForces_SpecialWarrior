package co.specialforce.view.activity.unitSearch

import co.specialforce.base.BaseContract

interface UnitSearchContract {
    interface View: BaseContract.BaseView<Presenter>{ }

    interface Presenter : BaseContract.BasePresenter { }
}