package co.specialforce.view.fragment.social

import co.specialforce.data.model.FriendModel
import co.specialforce.data.response.getFriend.Friend

class SocialPresenter(private val view: SocialContract.View) : SocialContract.Presenter,
    SocialContract.Model.GetFriendFinishedListener {

    private var model: FriendModel = FriendModel(this)

    lateinit var adapter : SocialFragment.FriendAdapter

    override fun setPresenterAdapter(friendAdapter: SocialFragment.FriendAdapter) {
        adapter = friendAdapter
    }

    override fun start() {
        view.presenter = this
    }

    override fun getFriend() {
        model.getFriend(this)
    }

    override fun getFriendFinished(friendList: List<Friend>?) {
        adapter.setItem(ArrayList(friendList!!))
        adapter.notifyDataSetChanged()
    }
}