package co.specialforce.view.fragment.home

import co.specialforce.base.BaseContract

interface HomeContract {
    interface View: BaseContract.BaseView<Presenter>

    interface Presenter: BaseContract.BasePresenter {}
}