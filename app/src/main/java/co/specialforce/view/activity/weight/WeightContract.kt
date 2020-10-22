package co.specialforce.view.activity.weight

import co.specialforce.base.BaseContract
import co.specialforce.data.response.getWeight.WeightArray
import co.specialforce.data.response.getWeight.WeightMinMaxAvg

interface WeightContract {
    interface Model{
        interface GetWeightFinishedListener{
            fun getWeightFinished(weightList: List<WeightArray>?, weightMinMaxAvg: WeightMinMaxAvg?)
        }
        fun weightInput(weight: Float)
        fun getWeight(listener: GetWeightFinishedListener)
    }
    interface View: BaseContract.BaseView<Presenter>{
        fun setChartData(weightList: List<WeightArray>?, weightMinMaxAvg: WeightMinMaxAvg?)
    }

    interface Presenter : BaseContract.BasePresenter {
        fun weightInput(weight: Float)
        fun getWeight()
    }
}