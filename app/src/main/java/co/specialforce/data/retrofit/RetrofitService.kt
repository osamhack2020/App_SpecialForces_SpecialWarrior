package co.specialforce.data.retrofit

import co.specialforce.data.request.*
import co.specialforce.data.response.HeartInputResponse
import co.specialforce.data.response.JoinResponse
import co.specialforce.data.response.LoginResponse
import co.specialforce.data.response.WeightInputResponse
import co.specialforce.data.response.getHeart.GetHeartResponse
import co.specialforce.data.response.getWeight.GetWeightResponse
import co.specialforce.data.response.unitSearch.UnitSearchResponse
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

    @POST("/api/v1/member/find_afflication")
    fun searchUnit(@Body request : UnitSearchRequest) : Call<UnitSearchResponse>

    @GET("/api/v1/heartrate/get_heartrate_profile_data")
    fun getHeart(@Header("Authorization") token: String) : Call<GetHeartResponse>

    @POST("/api/v1/heartrate/add_heartrate_profile")
    fun heartInput(@Header("Authorization") token: String,
                   @Body request : HeartInputRequest) : Call<HeartInputResponse>
}