package co.specialforce.view.fragment.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import co.specialforce.R
import co.specialforce.base.BaseFragment
import co.specialforce.data.response.getProfile.Profile
import co.specialforce.view.activity.heart.HeartActivity
import co.specialforce.view.activity.sleep.SleepActivity
import co.specialforce.view.activity.weight.WeightActivity
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), HomeContract.View, View.OnClickListener {
    override val layoutRes: Int
        get() = R.layout.fragment_home

    override lateinit var presenter: HomeContract.Presenter

    override fun setView(view: View?, savedInstanceState: Bundle?, arguments: Bundle?) {
        presenter = HomePresenter(this@HomeFragment)
        presenter.start()

        home_weight_card_view.setOnClickListener(this)
        home_sleep_card_view.setOnClickListener(this)
        home_heart_card_view.setOnClickListener(this)
        home_food_card_view.setOnClickListener(this)

        presenter.getProfile()
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.home_weight_card_view
                    -> startActivity(Intent(activity, WeightActivity::class.java))
            R.id.home_heart_card_view
                    -> startActivity(Intent(activity, HeartActivity::class.java))
            R.id.home_sleep_card_view
                    -> startActivity(Intent(activity, SleepActivity::class.java))
        }
    }

    override fun setView(profile: Profile?) {
        var level : String? = null
        when(profile?.level){
            "0" -> level = "이병"
            "1" -> level = "일병"
            "2" -> level = "상병"
            "3" -> level = "병장"
        }
        home_name_text.text = profile?.name + " " + level + "님"
    }

    override fun isViewActive() : Boolean = isViewActive()
}