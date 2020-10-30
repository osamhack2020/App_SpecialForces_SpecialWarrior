package co.specialforce.data.model

import android.util.Log
import co.specialforce.data.response.getProfile.GetProfileResponse
import co.specialforce.data.retrofit.RetrofitGenerator
import co.specialforce.data.user.UserInformation
import co.specialforce.view.fragment.home.HomeContract
import co.specialforce.view.fragment.home.HomePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeModel(private val presenter : HomePresenter): HomeContract.Model {
    override fun getProfile(listener: HomeContract.Model.GetProfileListener) {
        val getProfileCall = RetrofitGenerator.create().getProfile("Bearer "+
                UserInformation.token)
        getProfileCall.enqueue((object : Callback<GetProfileResponse> {
            override fun onResponse(call: Call<GetProfileResponse>, response: Response<GetProfileResponse>) {
                if(response.code()==200) {
                    if(response.body()?.result?.size!=0){
                        listener.getProfileFinished(response.body()?.result?.get(0))
                        UserInformation.unit=response.body()?.result?.get(0)?.unit_full_name
                    }
                }else{
                    if(response.body()?.result?.size!=0){
                        listener.getProfileFinished(response.body()?.result?.get(0))
                    }
                }
            }
            override fun onFailure(call: Call<GetProfileResponse>, t: Throwable) {
                Log.d("Get Profile Failed", "Get Profile Failed")
            }
        }))
    }
}