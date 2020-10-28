package co.specialforce.data.model

import android.util.Log
import co.specialforce.data.request.UnitSearchRequest
import co.specialforce.data.response.unitSearch.UnitSearchResponse
import co.specialforce.data.retrofit.RetrofitGenerator
import co.specialforce.view.activity.unitSearch.UnitSearchContract
import co.specialforce.view.activity.unitSearch.UnitSearchPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UnitSearchModel(private val presenter: UnitSearchPresenter): UnitSearchContract.Model {
    override fun searchUnit(unitName: String, listener: UnitSearchContract.Model.SearchFinishedListener) {
        val unitCall = RetrofitGenerator.create().searchUnit(UnitSearchRequest(unitName))
        unitCall.enqueue((object : Callback<UnitSearchResponse> {
            override fun onResponse(call: Call<UnitSearchResponse>, response: Response<UnitSearchResponse>) {
                if(response.code()==200) {
                    listener.searchFinished(response.body()?.result)
                }else{
                    Log.d("Search Failed", "Search Failed")
                }
            }
            override fun onFailure(call: Call<UnitSearchResponse>, t: Throwable) {
                Log.d("Search Failed", "Search Failed")
            }
        }))
    }
}