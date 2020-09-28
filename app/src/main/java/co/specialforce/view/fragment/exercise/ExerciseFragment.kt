package co.specialforce.view.fragment.exercise

import android.os.Bundle
import android.view.View
import co.specialforce.R
import co.specialforce.base.BaseFragment

class ExerciseFragment: BaseFragment(), ExerciseContract.View {
    override val layoutRes: Int
        get() = R.layout.fragment_exercise

    override lateinit var presenter: ExerciseContract.Presenter

    override fun setView(view: View?, savedInstanceState: Bundle?, arguments: Bundle?) {
        presenter = ExercisePresenter(this@ExerciseFragment)
    }

    override fun isViewActive(): Boolean = isViewActive()
}