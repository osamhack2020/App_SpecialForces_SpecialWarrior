package co.specialforce.data.model

import android.util.Log
import co.specialforce.data.request.JoinRequest
import co.specialforce.data.response.JoinResponse
import co.specialforce.data.retrofit.RetrofitGenerator
import co.specialforce.view.activity.join.JoinContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JoinModel: JoinContract.Model {
    override fun join(joinRequest: JoinRequest, listener: JoinContract.Model.JoinFinishedListener) {
        val loginCall = RetrofitGenerator.create().join(joinRequest)
        loginCall.enqueue((object : Callback<JoinResponse> {
            override fun onResponse(call: Call<JoinResponse>, response: Response<JoinResponse>) {
                if(response.code()==200) {
                    Log.d("Login Success", "Login Success")
                    listener.joinFinished()
                }else{
                    Log.d("Login Failed", "Login Failed")
                    listener.joinFailed()
                }
            }
            override fun onFailure(call: Call<JoinResponse>, t: Throwable) {
                Log.d("Login Failed", "Login Failed")
                listener.joinFailed()
            }
        }))
    }
}