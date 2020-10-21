package co.specialforce.view.activity.login

import co.specialforce.base.BaseContract

interface LoginContract {
    interface Model{
        interface LoginFinishedListener{
            fun loginFinished()
            fun loginFailed()
        }
        fun login(username: String, password: String, checkedAutoLogin: Boolean,
                  listener: LoginFinishedListener)
        fun checkAutoLogin()
        fun saveAutoLogin(id: String, pw: String)
    }

    interface View: BaseContract.BaseView<Presenter>{
        fun showLoginFailed()
    }

    interface Presenter: BaseContract.BasePresenter{
        fun checkAutoLogin()
        fun login(username: String, password: String, checkedAutoLogin: Boolean)
        fun autoLoginCheckFinished(id: String, pw: String)
    }
}