package co.specialforce.view.main

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {

    override fun start() {
        view.presenter = this
    }
}