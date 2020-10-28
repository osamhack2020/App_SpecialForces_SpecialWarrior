package co.specialforce.view.activity.unitSearch

class UnitSearchPresenter(private val view: UnitSearchContract.View) : UnitSearchContract.Presenter {
    override fun start() {
        view.presenter = this
    }
}