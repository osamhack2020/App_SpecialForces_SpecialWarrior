package co.specialforce.view.activity.unitSearch

import co.specialforce.base.BaseContract
import co.specialforce.data.response.unitSearch.UnitArray

interface UnitSearchContract {
    interface Model{
        interface SearchFinishedListener{
            fun searchFinished(unitList : List<UnitArray>?)
        }
        fun searchUnit(unitName : String, listener: SearchFinishedListener)
    }

    interface View: BaseContract.BaseView<Presenter>{
        fun finishUnitSearch(unitCode : String)
    }

    interface Presenter : BaseContract.BasePresenter {
        fun searchUnit(unitName: String)
        fun sendUnitCode(unitCode : String)
        fun setPresenterAdapter(unitAdapter: UnitSearchActivity.UnitAdapter)
    }
}