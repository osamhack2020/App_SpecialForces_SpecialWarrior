package co.specialforce.data.model

import android.util.Log
import co.specialforce.data.request.WeightInputRequest
import co.specialforce.data.response.WeightInputResponse
import co.specialforce.data.response.getWeight.GetWeightResponse
import co.specialforce.data.retrofit.RetrofitGenerator
import co.specialforce.data.user.UserInformation
import co.specialforce.view.activity.weight.WeightContract
import co.specialforce.view.activity.weight.WeightPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeightModel(private val presenter: WeightPresenter): WeightContract.Model {
    override fun weightInput(weight: Float) {
        val weightInputCall = RetrofitGenerator.create().weightInput("Bearer "+
                UserInformation.token, WeightInputRequest(weight)
        )
        weightInputCall.enqueue((object : Callback<WeightInputResponse> {
            override fun onResponse(call: Call<WeightInputResponse>, response: Response<WeightInputResponse>) {
                if(response.code()==200) {
                    Log.d("Weight Input Success", "Weight Input Success")
                }else{
                    Log.d("Weight Input Failed", "Weight Input Failed")
                }
            }
            override fun onFailure(call: Call<WeightInputResponse>, t: Throwable) {
                Log.d("Weight Input Failed", "Weight Input Failed")
            }
        }))
    }

    override fun getWeight(listener: WeightContract.Model.GetWeightFinishedListener) {
        val getWeightCall = RetrofitGenerator.create().getWeight("Bearer "+
                UserInformation.token)
        getWeightCall.enqueue((object : Callback<GetWeightResponse> {
            override fun onResponse(call: Call<GetWeightResponse>, response: Response<GetWeightResponse>) {
                if(response.code()==200) {
                    listener.getWeightFinished(response.body()?.result, response.body()?.min_max_avg)
                }else{
                    listener.getWeightFinished(response.body()?.result, response.body()?.min_max_avg)
                }
            }
            override fun onFailure(call: Call<GetWeightResponse>, t: Throwable) {
                Log.d("Get Weight Failed", "Get Weight Failed")
            }
        }))
    }
}