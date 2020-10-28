package co.specialforce.view.activity.unitSearch

import co.specialforce.R
import co.specialforce.base.BaseActivity

class UnitSearchActivity : BaseActivity(), UnitSearchContract.View{

    override val layoutRes: Int
        get() = R.layout.activity_unit_search

    override lateinit var presenter: UnitSearchContract.Presenter

    override fun initView() {
        presenter = UnitSearchPresenter(this@UnitSearchActivity)
        presenter.start()

        // something going on
    }

    override fun isViewActive(): Boolean = checkActive()
}