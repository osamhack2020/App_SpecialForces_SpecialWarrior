package co.specialforce.view.fragment.social

import android.os.Bundle
import android.view.View
import co.specialforce.R
import co.specialforce.base.BaseFragment

class SocialFragment: BaseFragment(), SocialContract.View {
    override val layoutRes: Int
        get() = R.layout.fragment_social

    override lateinit var presenter : SocialContract.Presenter

    override fun setView(view: View?, savedInstanceState: Bundle?, arguments: Bundle?) {
        presenter = SocialPresenter(this@SocialFragment)
    }

    override fun isViewActive(): Boolean = isViewActive()
}