package co.specialforce.data.retrofit

import co.specialforce.data.response.foodSearch.FoodSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FsRetrofitService {
    @GET("/api/a32afdcafdcb4210aeca/I2790/json/1/20/DESC_KOR={name}")
    fun searchFood(@Path("name") string: String) : Call<FoodSearchResponse>
}