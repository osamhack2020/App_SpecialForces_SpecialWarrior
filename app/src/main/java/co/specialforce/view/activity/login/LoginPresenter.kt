package co.specialforce.view.activity.login

import android.content.Context
import android.content.Intent
import co.specialforce.data.model.LoginModel
import co.specialforce.view.activity.main.MainActivity

class LoginPresenter(private val view: LoginContract.View, private val context: Context): LoginContract.Presenter,
    LoginContract.Model.LoginFinishedListener {

    private var model: LoginModel = LoginModel(this, context)

    override fun start() {
        view.presenter = this
    }

    override fun login(username: String, password: String, checkedAutoLogin: Boolean) {
        model.login(username, password, checkedAutoLogin, this)
    }

    override fun checkAutoLogin() {
        model.checkAutoLogin()
    }

    override fun autoLoginCheckFinished(id: String, pw: String) {
        model.login(id, pw, false, this)
    }

    override fun loginFinished() {
        context.startActivity(Intent(context, MainActivity::class.java))
    }

    override fun loginFailed() {
        view.showLoginFailed()
    }
}