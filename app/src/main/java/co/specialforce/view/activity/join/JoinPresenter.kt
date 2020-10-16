package co.specialforce.view.activity.join

import android.content.Context
import android.content.Intent
import co.specialforce.data.model.JoinModel
import co.specialforce.data.request.JoinRequest
import co.specialforce.view.activity.login.LoginActivity

class JoinPresenter(private val view: JoinContract.View, private val context: Context)
    :JoinContract.Presenter, JoinContract.Model.JoinFinishedListener{

    private var model: JoinModel = JoinModel()

    override fun start() {
        view.presenter = this
    }

    override fun join(joinRequest: JoinRequest) {
        model.join(joinRequest, this)
    }

    override fun joinFinished() {
        view.showJoinFinished()
    }

    override fun joinFailed() {
        view.showJoinFailed()
    }
}