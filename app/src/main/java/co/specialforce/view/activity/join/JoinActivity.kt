package co.specialforce.view.activity.join

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Toast
import co.specialforce.R
import co.specialforce.base.BaseActivity
import co.specialforce.data.request.JoinRequest
import co.specialforce.view.activity.login.LoginActivity
import co.specialforce.view.activity.unitSearch.UnitSearchActivity
import kotlinx.android.synthetic.main.activity_join.*

class JoinActivity: BaseActivity(), JoinContract.View, View.OnClickListener {
    override val layoutRes: Int
        get() = R.layout.activity_join

    override lateinit var presenter: JoinContract.Presenter

    override fun initView() {
        presenter = JoinPresenter(this@JoinActivity, this)
        presenter.start()

        join_join_button.setOnClickListener(this)
        join_unit_search_button.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.join_join_button ->
                presenter.join(JoinRequest(join_id_edit.text.toString(),
                    join_pw_edit.text.toString(), getSelectedLevel(),
                    join_class_check_box.isChecked, join_army_num_edit.text.toString(),
                    join_unit_id_edit.text.toString().toInt(), join_name_edit.text.toString(),
                    join_email_edit.text.toString(), join_phone_edit.toString()))
            R.id.join_unit_search_button -> {
                startActivityForResult(Intent(this, UnitSearchActivity::class.java), 100)
            }
        }
    }

    override fun showJoinFailed() {
        Toast.makeText(this, "입력한 정보를 다시 확인해주세요", Toast.LENGTH_SHORT).show()
    }

    override fun showJoinFinished() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun getSelectedLevel(): Int {
        return when(join_spinner.selectedItem.toString()){
            "이병" -> 0
            "일병" -> 1
            "상병" -> 2
            "병장" -> 3
            else -> -1
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            join_unit_id_edit.setText(data!!.getStringExtra("Unit").toString())
        }
    }

    override fun isViewActive(): Boolean = checkActive()
}