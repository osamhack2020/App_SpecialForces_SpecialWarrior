package co.specialforce.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    abstract val layoutRes : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)

        initView()
        onClickEvent()
    }

    abstract fun initView()
    open fun onClickEvent() {}

    fun checkActive(): Boolean = !isFinishing
}