package co.specialforce.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.specialforce.R
import co.specialforce.base.BaseActivity

class MainActivity : BaseActivity(), MainContract.View {
    override val layoutRes: Int
        get() = R.layout.activity_main

    override lateinit var presenter: MainContract.Presenter

    override fun initView() {
        presenter = MainPresenter(this@MainActivity)
        presenter.start()
    }

    override fun isViewActive(): Boolean = checkActive()
}
