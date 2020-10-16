package co.specialforce.view.activity.login

import android.content.Intent
import android.view.View
import android.widget.Toast
import co.specialforce.R
import co.specialforce.base.BaseActivity
import co.specialforce.view.activity.join.JoinActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: BaseActivity(), LoginContract.View, View.OnClickListener {
    override val layoutRes: Int
        get() = R.layout.activity_login

    override lateinit var presenter: LoginContract.Presenter

    override fun initView() {
        presenter = LoginPresenter(this@LoginActivity, this)
        presenter.start()

        login_login_button.setOnClickListener(this)
        login_join_button.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.login_login_button ->
                presenter.login(login_id_edit.text.toString(), login_pw_edit.text.toString())
            R.id.login_join_button ->
                startActivity(Intent(this, JoinActivity::class.java))
        }
    }

    override fun showLoginFailed() {
        Toast.makeText(this, "아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
    }

    override fun isViewActive(): Boolean = checkActive()
}