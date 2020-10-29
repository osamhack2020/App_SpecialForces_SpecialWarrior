package co.specialforce.view.activity.sleep

import co.specialforce.data.model.SleepModel
import co.specialforce.data.response.getSleep.SleepArray
import co.specialforce.data.response.getSleep.SleepMinMaxAvg

class SleepPresenter(private val view: SleepContract.View) : SleepContract.Presenter,
    SleepContract.Model.GetSleepFinishedListener{

    private val model: SleepModel = SleepModel(this)

    override fun start() {
        view.presenter = this
    }

    override fun sleepInput(sleep: Int) {
        model.sleepInput(sleep)
    }

    override fun getSleep() {
        model.getSleep(this)
    }

    override fun getSleepFinished(sleepList: List<SleepArray>?, sleepMinMaxAvg: SleepMinMaxAvg?) {
        view.setChartData(sleepList, sleepMinMaxAvg)
    }
}