package co.specialforce.view.fragment.exercise

import co.specialforce.base.BaseContract

interface ExerciseContract {
    interface View: BaseContract.BaseView<Presenter>

    interface Presenter: BaseContract.BasePresenter {}
}