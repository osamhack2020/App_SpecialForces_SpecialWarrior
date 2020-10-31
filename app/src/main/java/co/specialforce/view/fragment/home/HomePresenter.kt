package co.specialforce.view.fragment.home

import co.specialforce.data.model.HomeModel
import co.specialforce.data.response.getInfo.DailyInfo
import co.specialforce.data.response.getProfile.Profile

class HomePresenter(private val view: HomeContract.View) : HomeContract.Presenter,
    HomeContract.Model.GetProfileListener {

    private val model: HomeModel = HomeModel(this)

    override fun start() {
        view.presenter = this
    }
    override fun getProfile() {
        model.getProfile(this)
    }

    override fun getProfileFinished(profile: Profile?) {
        view.setView(profile)
    }

    override fun getInfoFinished(info: DailyInfo?) {
        view.setInfo(info)
    }
}