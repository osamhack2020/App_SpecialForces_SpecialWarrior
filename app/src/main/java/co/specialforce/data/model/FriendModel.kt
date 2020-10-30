package co.specialforce.data.model

import android.util.Log
import co.specialforce.data.response.getFriend.GetFriendResponse
import co.specialforce.data.retrofit.RetrofitGenerator
import co.specialforce.data.user.UserInformation
import co.specialforce.view.fragment.social.SocialContract
import co.specialforce.view.fragment.social.SocialPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FriendModel (private val presenter: SocialPresenter): SocialContract.Model {
    override fun getFriend(listener: SocialContract.Model.GetFriendFinishedListener) {
        val friendCall = RetrofitGenerator.create().getFriend("Bearer "+
                UserInformation.token)
        friendCall.enqueue((object : Callback<GetFriendResponse> {
            override fun onResponse(call: Call<GetFriendResponse>, response: Response<GetFriendResponse>) {
                if(response.code()==200) {
                    listener.getFriendFinished(response.body()?.result)
                }else{
                    Log.d("Get Friend Failed", "Get Friend Failed")
                }
            }
            override fun onFailure(call: Call<GetFriendResponse>, t: Throwable) {
                Log.d("Get Friend Failed", "Get Friend Failed")
            }
        }))
    }
}