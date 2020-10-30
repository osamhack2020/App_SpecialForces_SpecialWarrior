package co.specialforce.view.fragment.social

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.specialforce.R
import co.specialforce.base.BaseFragment
import co.specialforce.data.response.getFriend.Friend
import kotlinx.android.synthetic.main.fragment_social.*
import kotlinx.android.synthetic.main.item_friend.view.*

class SocialFragment: BaseFragment(), SocialContract.View {
    private lateinit var recyclerViewAdapter: FriendAdapter

    override val layoutRes: Int
        get() = R.layout.fragment_social

    override lateinit var presenter : SocialContract.Presenter

    override fun setView(view: View?, savedInstanceState: Bundle?, arguments: Bundle?) {
        presenter = SocialPresenter(this@SocialFragment)
        presenter.start()

        val recyclerView = social_recycler_view
        recyclerViewAdapter = FriendAdapter(presenter)
        recyclerView.adapter = recyclerViewAdapter

        presenter.setPresenterAdapter(recyclerViewAdapter)
        presenter.getFriend()
    }

    override fun isViewActive(): Boolean = isViewActive()

    class FriendAdapter(val presenter: SocialContract.Presenter) : RecyclerView.Adapter<FriendAdapter.ViewHolder>(){
        private var friendList = ArrayList<Friend>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_friend,parent,false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val friend = friendList[position]
            with(holder){
                when(friend.level){
                    0 -> friendName.text = (friend.name + " 이병")
                    1 -> friendName.text = (friend.name + " 일병")
                    2 -> friendName.text = (friend.name + " 상병")
                    3 -> friendName.text = (friend.name + " 병장")
                }
            }
        }

        override fun getItemCount() = friendList.size

        fun setItem(friendList : ArrayList<Friend>){
            this.friendList = friendList
        }

        inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
            val friendName : TextView = view.item_friend_name
        }
    }
}