package co.specialforce.data.model

import android.util.Log
import co.specialforce.data.request.ExerciseInputRequest
import co.specialforce.data.request.GetExerciseRequest
import co.specialforce.data.response.ExerciseInputResponse
import co.specialforce.data.response.getExercise.GetExerciseResponse
import co.specialforce.data.retrofit.RetrofitGenerator
import co.specialforce.data.user.UserInformation
import co.specialforce.view.fragment.exercise.ExerciseContract
import co.specialforce.view.fragment.exercise.ExercisePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExerciseModel(private val presenter: ExercisePresenter): ExerciseContract.Model {
    override fun getExercise(date: String, listener: ExerciseContract.Model.GetExerciseFinishedListener) {
        for(exercise_id in 0..6){
            val getExerciseCall = RetrofitGenerator.create().getExercise("Bearer "+
                    UserInformation.token, GetExerciseRequest(date, exercise_id))
            getExerciseCall.enqueue((object : Callback<GetExerciseResponse> {
                override fun onResponse(call: Call<GetExerciseResponse>, response: Response<GetExerciseResponse>) {
                    if(response.code()==200) {
                        if(response.body()?.result?.size!=0){
                            listener.getExerciseFinished(response.body()?.result?.
                                get(response.body()?.result?.size!!-1))
                        }
                    }else{
                        if(response.body()?.result?.size!=0){
                            listener.getExerciseFinished(response.body()?.result?.
                                get(response.body()?.result?.size!!-1))
                        }
                    }
                }
                override fun onFailure(call: Call<GetExerciseResponse>, t: Throwable) {
                    Log.d("Get Exercise Failed", "Get Exercise Failed")
                }
            }))
        }
    }

    override fun inputExercise(request: ExerciseInputRequest) {
        val exerciseInputCall = RetrofitGenerator.create().exerciseInput("Bearer "+
                UserInformation.token, request)
        exerciseInputCall.enqueue((object : Callback<ExerciseInputResponse> {
            override fun onResponse(call: Call<ExerciseInputResponse>, response: Response<ExerciseInputResponse>) {
                if(response.code()==200) {
                    Log.d("Exercise Input Success", "Exercise Input Success")
                }else{
                    Log.d("Exercise Input Failed", "Exercise Input Failed")
                }
            }
            override fun onFailure(call: Call<ExerciseInputResponse>, t: Throwable) {
                Log.d("Exercise Input Failed", "Exercise Input Failed")
            }
        }))
    }
}