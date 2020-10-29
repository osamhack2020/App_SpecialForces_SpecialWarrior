package co.specialforce.view.activity.heart

import co.specialforce.data.model.HeartModel
import co.specialforce.data.response.getHeart.HeartArray
import co.specialforce.data.response.getHeart.HeartDailyData
import co.specialforce.data.response.getHeart.HeartMinMaxAvg

class HeartPresenter(private val view: HeartContract.View): HeartContract.Presenter,
    HeartContract.Model.GetHeartFinishedListener {

    private val model : HeartModel = HeartModel(this)

    override fun start() {
        view.presenter = this
    }

    override fun heartInput(heart: Int) {
        model.heartInput(heart)
    }

    override fun getHeart() {
        model.getHeart(this)
    }

    override fun getHeartFinished(heartList: List<HeartDailyData>?) {
        view.setChartData(heartList)
    }
}