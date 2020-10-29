package co.specialforce.data.model

import android.util.Log
import co.specialforce.data.request.HeartInputRequest
import co.specialforce.data.response.HeartInputResponse
import co.specialforce.data.response.getHeart.GetHeartResponse
import co.specialforce.data.retrofit.RetrofitGenerator
import co.specialforce.data.user.UserInformation
import co.specialforce.view.activity.heart.HeartContract
import co.specialforce.view.activity.heart.HeartPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeartModel(private val presenter: HeartPresenter): HeartContract.Model {
    override fun heartInput(heart: Int) {
        val heartInputCall = RetrofitGenerator.create().heartInput("Bearer "+
                UserInformation.token, HeartInputRequest(heart)
        )
        heartInputCall.enqueue((object : Callback<HeartInputResponse> {
            override fun onResponse(call: Call<HeartInputResponse>, response: Response<HeartInputResponse>) {
                if(response.code()==200) {
                    Log.d("Heart Input Success", "Heart Input Success")
                }else{
                    Log.d("Heart Input Failed", "Heart Input Failed")
                }
            }
            override fun onFailure(call: Call<HeartInputResponse>, t: Throwable) {
                Log.d("Heart Input Failed", "Heart Input Failed")
            }
        }))
    }

    override fun getHeart(listener: HeartContract.Model.GetHeartFinishedListener) {
        val getHeartCall = RetrofitGenerator.create().getHeart("Bearer "+
                UserInformation.token)
        getHeartCall.enqueue((object : Callback<GetHeartResponse> {
            override fun onResponse(call: Call<GetHeartResponse>, response: Response<GetHeartResponse>) {
                if(response.code()==200) {
                    listener.getHeartFinished(response.body()?.result)
                }else{
                    listener.getHeartFinished(response.body()?.result)
                }
            }
            override fun onFailure(call: Call<GetHeartResponse>, t: Throwable) {
                Log.d("Get Heart Failed", "Get Heart Failed")
            }
        }))
    }
}