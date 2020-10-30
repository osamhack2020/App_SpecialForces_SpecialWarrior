package co.specialforce.view.fragment.exercise

import co.specialforce.data.model.ExerciseModel
import co.specialforce.data.request.ExerciseInputRequest
import co.specialforce.data.response.getExercise.Exercise

class ExercisePresenter(private val view: ExerciseContract.View) : ExerciseContract.Presenter,
    ExerciseContract.Model.GetExerciseFinishedListener {

    private val model: ExerciseModel = ExerciseModel(this)

    override fun start() {
        view.presenter = this
    }

    override fun getExercise(date: String) {
        model.getExercise(date, this)
    }

    override fun getExerciseFinished(exercise: Exercise?) {
        view.setExerciseView(exercise)
    }

    override fun inputExercise(request: ExerciseInputRequest) {
        model.inputExercise(request)
    }
}