package co.specialforce.view.activity.join

import co.specialforce.base.BaseContract
import co.specialforce.data.request.JoinRequest

interface JoinContract {
    interface Model{
        interface JoinFinishedListener{
            fun joinFinished()
            fun joinFailed()
        }
        fun join(joinRequest: JoinRequest, listener: JoinFinishedListener)
    }
    interface View: BaseContract.BaseView<Presenter> {
        fun showJoinFailed()
        fun showJoinFinished()
    }

    interface Presenter: BaseContract.BasePresenter{
        fun join(joinRequest: JoinRequest)
    }
}