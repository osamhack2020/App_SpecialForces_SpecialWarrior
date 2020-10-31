package co.specialforce.view.activity.foodSearch

import co.specialforce.data.model.FoodSearchModel
import co.specialforce.data.response.foodSearch.Food

class FoodSearchPresenter(private val view: FoodSearchContract.View) : FoodSearchContract.Presenter,
    FoodSearchContract.Model.SearchFinishedListener{

    private var model: FoodSearchModel = FoodSearchModel(this)

    lateinit var adapter: FoodSearchActivity.FoodAdapter

    override fun setPresenterAdapter(foodAdapter: FoodSearchActivity.FoodAdapter) {
        adapter = foodAdapter
    }

    override fun start() {
        view.presenter=this
    }

    override fun searchFood(name: String) {
        model.searchFood(name, this)
    }

    override fun searchFinished(foodList: List<Food>?) {
        adapter.setItem(ArrayList(foodList!!))
        adapter.notifyDataSetChanged()
    }

    override fun sendFoodInfo(food: Food) {
        view.finishFoodSearch(food)
    }

    override fun foodInput(cal: Int) {
        model.foodInput(cal)
    }
}