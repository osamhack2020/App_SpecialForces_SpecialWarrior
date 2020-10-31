package co.specialforce.data.model

import android.util.Log
import co.specialforce.data.request.FoodInputRequest
import co.specialforce.data.response.foodSearch.FoodSearchResponse
import co.specialforce.data.response.getCal.FoodInputResponse
import co.specialforce.data.retrofit.FsRetrofitGenerator
import co.specialforce.data.retrofit.RetrofitGenerator
import co.specialforce.data.user.UserInformation
import co.specialforce.view.activity.foodSearch.FoodSearchContract
import co.specialforce.view.activity.foodSearch.FoodSearchPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodSearchModel(private val presenter : FoodSearchPresenter): FoodSearchContract.Model {
    override fun searchFood(name: String,
        listener: FoodSearchContract.Model.SearchFinishedListener) {
        val foodSearchCall = FsRetrofitGenerator.create().searchFood(name)
        foodSearchCall.enqueue((object : Callback<FoodSearchResponse> {
            override fun onResponse(call: Call<FoodSearchResponse>, response: Response<FoodSearchResponse>) {
                if(response.code()==200) {
                    if(response.body()?.I2790?.row!=null)
                        listener.searchFinished(response.body()?.I2790?.row)
                }else{
                    Log.d("Search Failed", "Search Failed")
                }
            }
            override fun onFailure(call: Call<FoodSearchResponse>, t: Throwable) {
                Log.d("Search Failed", "Search Failed")
            }
        }))
    }

    override fun foodInput(cal: Int) {
        val foodInputCall = RetrofitGenerator.create().foodInput("Bearer "+
                UserInformation.token, FoodInputRequest(cal))
        foodInputCall.enqueue((object : Callback<FoodInputResponse> {
            override fun onResponse(call: Call<FoodInputResponse>, response: Response<FoodInputResponse>) {
                if(response.code()==200) {
                }else{
                }
            }
            override fun onFailure(call: Call<FoodInputResponse>, t: Throwable) {
            }
        }))}
}