package co.specialforce.view.fragment.exercise

import co.specialforce.base.BaseContract
import co.specialforce.data.request.ExerciseInputRequest
import co.specialforce.data.response.getExercise.Exercise

interface ExerciseContract {
    interface Model{
        interface GetExerciseFinishedListener{
            fun getExerciseFinished(exercise: Exercise?)
        }
        fun getExercise(date: String, listener: GetExerciseFinishedListener)
        fun inputExercise(request: ExerciseInputRequest)
    }

    interface View: BaseContract.BaseView<Presenter>{
        fun setExerciseView(exercise: Exercise?)
    }

    interface Presenter : BaseContract.BasePresenter {
        fun getExercise(date: String)
        fun inputExercise(request: ExerciseInputRequest)
    }
}