package co.specialforce.view.activity.weight

import co.specialforce.data.model.WeightModel
import co.specialforce.data.response.getWeight.WeightArray
import co.specialforce.data.response.getWeight.WeightMinMaxAvg

class WeightPresenter(private val view: WeightContract.View) : WeightContract.Presenter,
    WeightContract.Model.GetWeightFinishedListener{

    private val model : WeightModel = WeightModel(this)

    override fun start() {
        view.presenter = this
    }

    override fun weightInput(weight: Float) {
        model.weightInput(weight)
    }

    override fun getWeight() {
        model.getWeight(this)
    }

    override fun getWeightFinished(weightList: List<WeightArray>?, weightMinMaxAvg: WeightMinMaxAvg?) {
        view.setChartData(weightList, weightMinMaxAvg)
    }
}