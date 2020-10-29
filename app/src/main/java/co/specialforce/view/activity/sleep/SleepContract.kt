package co.specialforce.view.activity.sleep

import co.specialforce.base.BaseContract
import co.specialforce.data.response.getSleep.SleepArray
import co.specialforce.data.response.getSleep.SleepMinMaxAvg

interface SleepContract {
    interface Model{
        interface GetSleepFinishedListener{
            fun getSleepFinished(sleepList: List<SleepArray>?, sleepMinMaxAvg: SleepMinMaxAvg?)
        }
        fun sleepInput(sleep: Int)
        fun getSleep(listener: GetSleepFinishedListener)
    }
    interface View: BaseContract.BaseView<Presenter>{
        fun setChartData(sleepList: List<SleepArray>?, sleepMinMaxAvg: SleepMinMaxAvg?)
    }

    interface Presenter : BaseContract.BasePresenter {
        fun sleepInput(sleep: Int)
        fun getSleep()
    }
}