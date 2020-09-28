package co.specialforce.view.activity.main

import android.view.MenuItem
import android.view.View
import co.specialforce.R
import co.specialforce.base.BaseActivity
import co.specialforce.view.fragment.exercise.ExerciseFragment
import co.specialforce.view.fragment.home.HomeFragment
import co.specialforce.view.fragment.social.SocialFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity(), MainContract.View, BottomNavigationView.OnNavigationItemSelectedListener {
    override val layoutRes: Int
        get() = R.layout.activity_main

    override lateinit var presenter: MainContract.Presenter

    override fun initView() {
        presenter = MainPresenter(this@MainActivity)
        presenter.start()

        val bottomNavigationView = findViewById<View>(R.id.main_navigation_view) as BottomNavigationView
        val fragmentHome = HomeFragment()
        supportFragmentManager.beginTransaction().replace(R.id.main_frame_layout,fragmentHome).commitAllowingStateLoss()
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(menu: MenuItem): Boolean {
        when(menu.itemId) {
            R.id.navigation_home -> {
                val fragmentHome = HomeFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frame_layout, fragmentHome).commitAllowingStateLoss()
            }
            R.id.navigation_exercise -> {
                val fragmentExercise = ExerciseFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frame_layout, fragmentExercise).commitAllowingStateLoss()
            }
            R.id.navigation_social -> {
                val fragmentSocial = SocialFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frame_layout, fragmentSocial).commitAllowingStateLoss()
            }
        }
        return true
    }

    override fun isViewActive(): Boolean = checkActive()
}
