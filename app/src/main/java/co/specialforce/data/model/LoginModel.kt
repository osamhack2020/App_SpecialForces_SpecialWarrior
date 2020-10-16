package co.specialforce.data.model

import android.util.Log
import co.specialforce.data.request.LoginRequest
import co.specialforce.data.response.LoginResponse
import co.specialforce.data.retrofit.RetrofitGenerator
import co.specialforce.data.user.UserInformation
import co.specialforce.view.activity.login.LoginContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginModel: LoginContract.Model {
    override fun login(username: String, password: String, listener: LoginContract.Model.LoginFinishedListener) {
        val loginCall = RetrofitGenerator.create().login(LoginRequest("password", "app",username, password))
        loginCall.enqueue((object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.code()==200) {
                    UserInformation.token = response.body()?.access_token
                    listener.loginFinished()
                }else{
                    Log.d("Login Failed", "Login Failed")
                    listener.loginFailed()
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("Login Failed", "Login Failed")
                listener.loginFailed()
            }
        }))
    }
}