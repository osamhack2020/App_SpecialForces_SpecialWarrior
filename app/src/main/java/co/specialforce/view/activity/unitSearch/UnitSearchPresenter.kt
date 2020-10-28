package co.specialforce.view.activity.unitSearch

import co.specialforce.data.model.UnitSearchModel
import co.specialforce.data.response.unitSearch.UnitArray

class UnitSearchPresenter(private val view: UnitSearchContract.View) : UnitSearchContract.Presenter,
    UnitSearchContract.Model.SearchFinishedListener{

    private var model: UnitSearchModel = UnitSearchModel(this)

    lateinit var adapter: UnitSearchActivity.UnitAdapter

    override fun setPresenterAdapter(unitAdapter: UnitSearchActivity.UnitAdapter){
        adapter = unitAdapter
    }

    override fun start() {
        view.presenter = this
    }

    override fun searchUnit(unitName: String) {
        model.searchUnit(unitName, this)
    }

    override fun searchFinished(unitList : List<UnitArray>?) {
        adapter.setItem(ArrayList(unitList!!))
        adapter.notifyDataSetChanged()
    }

    override fun sendUnitCode(unitCode: String) {
        view.finishUnitSearch(unitCode)
    }
}