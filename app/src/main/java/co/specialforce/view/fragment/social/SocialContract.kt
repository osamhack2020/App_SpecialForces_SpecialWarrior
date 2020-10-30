package co.specialforce.view.fragment.social

import co.specialforce.base.BaseContract
import co.specialforce.data.response.getFriend.Friend

interface SocialContract {
    interface Model{
        interface GetFriendFinishedListener{
            fun getFriendFinished(friendList : List<Friend>?)
        }
        fun getFriend(listener: GetFriendFinishedListener)
    }

    interface View: BaseContract.BaseView<Presenter>{
    }

    interface Presenter : BaseContract.BasePresenter {
        fun getFriend()
        fun setPresenterAdapter(adapter: SocialFragment.FriendAdapter)
    }
}