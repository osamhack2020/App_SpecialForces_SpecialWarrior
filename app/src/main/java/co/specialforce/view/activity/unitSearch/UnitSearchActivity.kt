package co.specialforce.view.activity.unitSearch

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.specialforce.R
import co.specialforce.base.BaseActivity
import co.specialforce.data.response.unitSearch.UnitArray
import kotlinx.android.synthetic.main.activity_unit_search.*
import kotlinx.android.synthetic.main.item_unit_search.view.*

class UnitSearchActivity : BaseActivity(), UnitSearchContract.View, View.OnClickListener{
    lateinit var recyclerViewAdapter : UnitAdapter

    override val layoutRes: Int
        get() = R.layout.activity_unit_search

    override lateinit var presenter: UnitSearchContract.Presenter

    override fun initView() {
        presenter = UnitSearchPresenter(this@UnitSearchActivity)
        presenter.start()

        val recyclerView = unit_search_recycler
        recyclerViewAdapter = UnitAdapter(presenter)
        recyclerView.adapter = recyclerViewAdapter

        presenter.setPresenterAdapter(recyclerViewAdapter)

        unit_search_button.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.unit_search_button -> {
                presenter.searchUnit(unit_search_edit.text.toString())
            }
        }
    }

    override fun finishUnitSearch(unitCode: String) {
        val intent = Intent()
        intent.putExtra("Unit", unitCode)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun isViewActive(): Boolean = checkActive()

    class UnitAdapter(val presenter: UnitSearchContract.Presenter) : RecyclerView.Adapter<UnitAdapter.ViewHolder>(){
        private var unitList = ArrayList<UnitArray>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_unit_search,parent,false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val unit = unitList[position]
            with(holder){
                unitName.text = unit.unit_full_name
            }
            holder.unitName.setOnClickListener{
                presenter.sendUnitCode(unit.unit_id)
            }
        }

        override fun getItemCount() = unitList.size

        fun setItem(unitList : ArrayList<UnitArray>){
            this.unitList = unitList
        }

        inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
            val unitName : TextView = view.item_unit_text
        }
    }
}