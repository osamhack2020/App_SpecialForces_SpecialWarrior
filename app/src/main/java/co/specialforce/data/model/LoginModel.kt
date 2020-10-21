package co.specialforce.data.model

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import co.specialforce.data.request.LoginRequest
import co.specialforce.data.response.LoginResponse
import co.specialforce.data.retrofit.RetrofitGenerator
import co.specialforce.data.user.UserInformation
import co.specialforce.view.activity.login.LoginContract
import co.specialforce.view.activity.login.LoginPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginModel(private val presenter: LoginPresenter, context: Context): LoginContract.Model {
    private var sharedPreferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    init{sharedPreferences = context.getSharedPreferences("pref", 0)}

    override fun login(username: String, password: String, checkedAutoLogin: Boolean,
                       listener: LoginContract.Model.LoginFinishedListener) {
        val loginCall = RetrofitGenerator.create().login(LoginRequest("password", "app",username, password))
        loginCall.enqueue((object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.code()==200) {
                    UserInformation.token = response.body()?.access_token
                    if(checkedAutoLogin){
                        saveAutoLogin(username, password)
                    }
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

    override fun checkAutoLogin() {
        val checkId: String? = sharedPreferences?.getString("id", null)
        val checkPw: String? = sharedPreferences?.getString("pw", null)

        if(checkId!=null && checkPw!=null){
            presenter.autoLoginCheckFinished(checkId, checkPw)
        }
    }

    override fun saveAutoLogin(id: String, pw: String) {
        editor= sharedPreferences?.edit()
        editor?.putString("id", id) // 00001
        editor?.putString("pw", pw) // test
        editor?.apply()
    }
}