package co.specialforce.view.fragment.home

import co.specialforce.base.BaseContract
import co.specialforce.data.response.getInfo.DailyInfo
import co.specialforce.data.response.getProfile.Profile

interface HomeContract {
    interface Model{
        interface GetProfileListener{
            fun getProfileFinished(profile: Profile?)
            fun getInfoFinished(info: DailyInfo?)
        }
        fun getProfile(listener: GetProfileListener)
    }

    interface View: BaseContract.BaseView<Presenter>{
        fun setView(profile: Profile?)
        fun setInfo(info: DailyInfo?)
    }

    interface Presenter: BaseContract.BasePresenter {
        fun getProfile()
    }
}