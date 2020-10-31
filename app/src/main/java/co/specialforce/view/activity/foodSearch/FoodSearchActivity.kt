package co.specialforce.view.activity.foodSearch

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import co.specialforce.R
import co.specialforce.base.BaseActivity
import co.specialforce.data.response.foodSearch.Food
import kotlinx.android.synthetic.main.activity_food_search.*
import kotlinx.android.synthetic.main.item_food_search.view.*

class FoodSearchActivity: BaseActivity(), FoodSearchContract.View, View.OnClickListener {
    private lateinit var recyclerViewAdapter : FoodAdapter

    override val layoutRes: Int
        get() = R.layout.activity_food_search

    override lateinit var presenter: FoodSearchContract.Presenter

    override fun initView() {
        presenter = FoodSearchPresenter(this@FoodSearchActivity)
        presenter.start()

        val recyclerView = food_search_recycler
        recyclerViewAdapter = FoodAdapter(presenter)
        recyclerView.adapter = recyclerViewAdapter

        presenter.setPresenterAdapter(recyclerViewAdapter)

        food_search_button.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.food_search_button -> {
                presenter.searchFood(food_search_edit.text.toString())
            }
        }
    }

    override fun finishFoodSearch(food: Food) {
        presenter.foodInput(food.NUTR_CONT1.toInt())
        val intent = Intent()
        intent.putExtra("Food", food.NUTR_CONT1)
        setResult(Activity.RESULT_OK, intent)
        Toast.makeText(this, food.NUTR_CONT1 + " 칼로리가 입력되었습니다",
            Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun isViewActive(): Boolean = checkActive()

    class FoodAdapter(val presenter: FoodSearchContract.Presenter) : RecyclerView.Adapter<FoodAdapter.ViewHolder>(){
        private var foodList = ArrayList<Food>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food_search,parent,false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val food = foodList[position]
            with(holder){
                foodName.text = food.DESC_KOR
                foodKcal.text = food.NUTR_CONT1
            }
            holder.foodName.setOnClickListener{
                presenter.sendFoodInfo(food)
            }
        }

        override fun getItemCount() = foodList.size

        fun setItem(foodList : ArrayList<Food>){
            this.foodList = foodList
        }

        inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
            val foodName : TextView = view.item_food_name
            val foodKcal : TextView = view.item_food_kcal
        }
    }
}