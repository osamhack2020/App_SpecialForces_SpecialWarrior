package co.specialforce.view.activity.login

import android.content.Context
import android.content.Intent
import co.specialforce.data.model.LoginModel
import co.specialforce.view.activity.main.MainActivity

class LoginPresenter(private val view: LoginContract.View, private val context: Context): LoginContract.Presenter,
    LoginContract.Model.LoginFinishedListener {

    private var model: LoginModel = LoginModel()

    override fun start() {
        view.presenter = this
    }

    override fun login(username: String, password: String) {
        model.login(username, password, this)
    }

    override fun loginFinished() {
        context.startActivity(Intent(context, MainActivity::class.java))
    }

    override fun loginFailed() {
        view.showLoginFailed()
    }
}