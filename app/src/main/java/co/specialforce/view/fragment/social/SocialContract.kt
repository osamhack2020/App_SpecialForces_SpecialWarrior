package co.specialforce.view.fragment.social

import co.specialforce.base.BaseContract

interface SocialContract {
    interface View: BaseContract.BaseView<Presenter>

    interface Presenter: BaseContract.BasePresenter {}
}