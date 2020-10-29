package co.specialforce.view.activity.heart

import co.specialforce.base.BaseContract
import co.specialforce.data.response.getHeart.HeartArray
import co.specialforce.data.response.getHeart.HeartDailyData
import co.specialforce.data.response.getHeart.HeartMinMaxAvg

interface HeartContract {
    interface Model{
        interface GetHeartFinishedListener{
            fun getHeartFinished(heartList: List<HeartDailyData>?)
        }
        fun heartInput(heart: Int)
        fun getHeart(listener: GetHeartFinishedListener)
    }
    interface View: BaseContract.BaseView<Presenter>{
        fun setChartData(heartList: List<HeartDailyData>?)
    }

    interface Presenter : BaseContract.BasePresenter {
        fun heartInput(heart: Int)
        fun getHeart()
    }
}