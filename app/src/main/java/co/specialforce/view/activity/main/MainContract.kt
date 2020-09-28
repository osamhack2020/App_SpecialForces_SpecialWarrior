package co.specialforce.view.activity.main

import co.specialforce.base.BaseContract

interface MainContract {
    interface View: BaseContract.BaseView<Presenter>{ }

    interface Presenter : BaseContract.BasePresenter { }
}