package co.specialforce.data.model

import android.util.Log
import co.specialforce.data.request.SleepInputRequest
import co.specialforce.data.response.SleepInputResponse
import co.specialforce.data.response.getSleep.GetSleepResponse
import co.specialforce.data.retrofit.RetrofitGenerator
import co.specialforce.data.user.UserInformation
import co.specialforce.view.activity.sleep.SleepContract
import co.specialforce.view.activity.sleep.SleepPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SleepModel(private val presenter: SleepPresenter): SleepContract.Model {
    override fun sleepInput(sleep: Int) {
        val sleepInputCall = RetrofitGenerator.create().sleepInput(
            "Bearer " +
                    UserInformation.token, SleepInputRequest(sleep)
        )
        sleepInputCall.enqueue((object : Callback<SleepInputResponse> {
            override fun onResponse(
                call: Call<SleepInputResponse>,
                response: Response<SleepInputResponse>
            ) {
                if (response.code() == 200) {
                    Log.d("Sleep Input Success", "Sleep Input Success")
                } else {
                    Log.d("Sleep Input Failed", "Sleep Input Failed")
                }
            }

            override fun onFailure(call: Call<SleepInputResponse>, t: Throwable) {
                Log.d("Sleep Input Failed", "Sleep Input Failed")
            }
        }))
    }

    override fun getSleep(listener: SleepContract.Model.GetSleepFinishedListener) {
        val getSleepCall = RetrofitGenerator.create().getSleep(
            "Bearer " +
                    UserInformation.token
        )
        getSleepCall.enqueue((object : Callback<GetSleepResponse> {
            override fun onResponse(
                call: Call<GetSleepResponse>,
                response: Response<GetSleepResponse>
            ) {
                if (response.code() == 200) {
                    listener.getSleepFinished(response.body()?.result, response.body()?.min_max_avg)
                } else {
                    listener.getSleepFinished(response.body()?.result, response.body()?.min_max_avg)
                }
            }

            override fun onFailure(call: Call<GetSleepResponse>, t: Throwable) {
                Log.d("Get Sleep Failed", "Get Sleep Failed")
            }
        }))
    }
}