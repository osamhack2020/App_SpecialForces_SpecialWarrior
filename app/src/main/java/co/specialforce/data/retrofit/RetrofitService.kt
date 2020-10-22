package co.specialforce.data.retrofit

import co.specialforce.data.request.JoinRequest
import co.specialforce.data.request.LoginRequest
import co.specialforce.data.request.WeightInputRequest
import co.specialforce.data.response.JoinResponse
import co.specialforce.data.response.LoginResponse
import co.specialforce.data.response.WeightInputResponse
import co.specialforce.data.response.getWeight.GetWeightResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface RetrofitService {
    @POST("/api/v1/auth/login")
    fun login(@Body request: LoginRequest) : Call<LoginResponse>

    @POST("/api/v1/member/register")
    fun join(@Body request : JoinRequest) : Call<JoinResponse>

    @POST("/api/v1/profile/set_weight")
    fun weightInput(@Header("Authorization") token: String,
                    @Body request : WeightInputRequest) : Call<WeightInputResponse>

    @GET("/api/v1/profile/get_weight_data")
    fun getWeight(@Header("Authorization") token: String) : Call<GetWeightResponse>
}