package co.specialforce.data.retrofit

import co.specialforce.data.request.JoinRequest
import co.specialforce.data.request.LoginRequest
import co.specialforce.data.response.JoinResponse
import co.specialforce.data.response.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitService {
    @POST("/api/v1/auth/login")
    fun login(@Body request: LoginRequest) : Call<LoginResponse>

    @POST("/api/v1/member/register")
    fun join(@Body request : JoinRequest) : Call<JoinResponse>
}