package co.specialforce.view.activity.weight

class WeightPresenter(private val view: WeightContract.View) : WeightContract.Presenter {

    override fun start() {
        view.presenter = this
    }
}