package co.specialforce.view.activity.weight

import co.specialforce.data.model.WeightModel

class WeightPresenter(private val view: WeightContract.View) : WeightContract.Presenter {
    private val model : WeightModel = WeightModel()

    override fun start() {
        view.presenter = this
    }

    override fun weightInput(weight: Float) {
        model.weightInput(weight)
    }
}