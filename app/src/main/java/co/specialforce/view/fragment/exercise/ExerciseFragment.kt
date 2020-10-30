package co.specialforce.view.fragment.exercise

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import co.specialforce.R
import co.specialforce.base.BaseFragment
import co.specialforce.data.request.ExerciseInputRequest
import co.specialforce.data.response.getExercise.Exercise
import kotlinx.android.synthetic.main.fragment_exercise.*
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class ExerciseFragment: BaseFragment(), ExerciseContract.View, View.OnClickListener {
    override val layoutRes: Int
        get() = R.layout.fragment_exercise

    override lateinit var presenter: ExerciseContract.Presenter

    override fun setView(view: View?, savedInstanceState: Bundle?, arguments: Bundle?) {
        presenter = ExercisePresenter(this@ExerciseFragment)
        presenter.start()

        exercise_calendar_button.setOnClickListener(this)
        exercise_input_button.setOnClickListener(this)

        val currentDateTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("yyyy-MM-dd").format(currentDateTime)
        exercise_calendar_button.text = dateFormat

        presenter.getExercise(exercise_calendar_button.text.toString())
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.exercise_calendar_button -> {
                val calendar = Calendar.getInstance()
                val dialog = DatePickerDialog(context!!, android.R.style.Theme_Material_Dialog_Alert,
                    DatePickerDialog.OnDateSetListener {
                        _, year, monthOfYear, dayOfMonth ->
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, monthOfYear)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val format = "yyyy-MM-dd"
                    exercise_calendar_button.text = SimpleDateFormat(format).format(calendar.time)
                    presenter.getExercise(exercise_calendar_button.text.toString())
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH))
                dialog.show()
            }

            R.id.exercise_input_button -> {
                val progressDialog = ProgressDialog(context, android.R.style.Theme_Material_Dialog_Alert)
                progressDialog.setMessage("기기를 측정 장치 위에 접촉해주세요")
                progressDialog.setCancelable(true)
                progressDialog.show()

                val exerciseId = Random().nextInt(6)

                val handler = Handler()
                val finishHandler = Handler()
                handler.postDelayed({
                    when(exerciseId){
                        0 -> progressDialog.setMessage("팔굽혀펴기 측정중입니다.")
                        1 -> progressDialog.setMessage("윗몸일으키기 측정중입니다.")
                        2 -> progressDialog.setMessage("뜀뛰기 측정중입니다.")
                        3 -> progressDialog.setMessage("벤치 프레스 측정중입니다.")
                        4 -> progressDialog.setMessage("렛 풀 다운 측정중입니다.")
                        5 -> progressDialog.setMessage("레그 익스텐션 측정중입니다.")
                        6 -> progressDialog.setMessage("레그 프레스 측정중입니다.")
                    }

                    finishHandler.postDelayed({
                        presenter.inputExercise(ExerciseInputRequest(exerciseId,
                            Random().nextInt(6)*10+40,
                            Random().nextInt(100)+20,
                            Random().nextInt(1000)
                        ))
                        progressDialog.dismiss()
                        Toast.makeText(context, "측정이 완료되었습니다", Toast.LENGTH_SHORT).show()
                        presenter.getExercise(exercise_calendar_button.text.toString())
                    }, 5000)
                }, 2000)
            }
        }
    }

    override fun isViewActive(): Boolean = isViewActive()

    override fun setExerciseView(exercise: Exercise?) {
        when(exercise?.exercise_id){
            0 -> exercise_0_count.text = exercise.exercise_count.toString()
            1 -> exercise_1_count.text = exercise.exercise_count.toString()
            2 -> {
                exercise_2_min_count.text = (exercise.exercise_time/60).toString()
                exercise_2_sec_count.text = (exercise.exercise_time%60).toString()
            }
            3 -> {
                exercise_3_count.text = exercise.exercise_count.toString()
                exercise_3_unit_count.text = exercise.exercise_weight.toString()
            }
            4 -> {
                exercise_4_count.text = exercise.exercise_count.toString()
                exercise_4_unit_count.text = exercise.exercise_weight.toString()
            }
            5 -> {
                exercise_5_count.text = exercise.exercise_count.toString()
                exercise_5_unit_count.text = exercise.exercise_weight.toString()
            }
            6 -> {
                exercise_6_count.text = exercise.exercise_count.toString()
                exercise_6_unit_count.text = exercise.exercise_weight.toString()
            }
        }
    }
}