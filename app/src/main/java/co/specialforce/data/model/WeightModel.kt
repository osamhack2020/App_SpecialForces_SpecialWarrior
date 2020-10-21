package co.specialforce.data.model

import android.util.Log
import co.specialforce.data.request.WeightInputRequest
import co.specialforce.data.response.WeightInputResponse
import co.specialforce.data.retrofit.RetrofitGenerator
import co.specialforce.data.user.UserInformation
import co.specialforce.view.activity.weight.WeightContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeightModel: WeightContract.Model {
    override fun weightInput(weight: Float) {
        val loginCall = RetrofitGenerator.create().weightInput("Bearer "+
                UserInformation.token, WeightInputRequest(weight)
        )
        loginCall.enqueue((object : Callback<WeightInputResponse> {
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
}