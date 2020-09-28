package co.specialforce.view.fragment.home

import android.os.Bundle
import android.view.View
import co.specialforce.R
import co.specialforce.base.BaseFragment

class HomeFragment : BaseFragment(), HomeContract.View {
    override val layoutRes: Int
        get() = R.layout.fragment_home

    override lateinit var presenter: HomeContract.Presenter

    override fun setView(view: View?, savedInstanceState: Bundle?, arguments: Bundle?) {
        presenter = HomePresenter(this@HomeFragment)
    }

    override fun isViewActive() : Boolean = isViewActive()
}