package co.specialforce.view.fragment.exercise

class ExercisePresenter(private val view: ExerciseContract.View) : ExerciseContract.Presenter {
    override fun start() {
        view.presenter = this
    }
}