package co.specialforce.view.fragment.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import co.specialforce.R
import co.specialforce.base.BaseFragment
import co.specialforce.view.activity.heart.HeartActivity
import co.specialforce.view.activity.weight.WeightActivity
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), HomeContract.View, View.OnClickListener {
    override val layoutRes: Int
        get() = R.layout.fragment_home

    override lateinit var presenter: HomeContract.Presenter

    override fun setView(view: View?, savedInstanceState: Bundle?, arguments: Bundle?) {
        presenter = HomePresenter(this@HomeFragment)

        home_weight_card_view.setOnClickListener(this)
        home_sleep_card_view.setOnClickListener(this)
        home_heart_card_view.setOnClickListener(this)
        home_food_card_view.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.home_weight_card_view
                    -> startActivity(Intent(activity, WeightActivity::class.java))
            R.id.home_heart_card_view
                    -> startActivity(Intent(activity, HeartActivity::class.java))
        }
    }

    override fun isViewActive() : Boolean = isViewActive()
}