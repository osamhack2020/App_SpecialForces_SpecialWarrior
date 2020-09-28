package co.specialforce.view.fragment.home

import android.content.Context

class HomePresenter(private val view: HomeContract.View) : HomeContract.Presenter {
    override fun start() {
        view.presenter = this
    }
}