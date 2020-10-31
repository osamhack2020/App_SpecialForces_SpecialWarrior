package co.specialforce.view.activity.foodSearch

import co.specialforce.base.BaseContract
import co.specialforce.data.response.foodSearch.Food

interface FoodSearchContract {
    interface Model{
        interface SearchFinishedListener{
            fun searchFinished(foodList : List<Food>?)
        }
        fun searchFood(name : String, listener: SearchFinishedListener)
        fun foodInput(cal : Int)
    }

    interface View: BaseContract.BaseView<Presenter>{
        fun finishFoodSearch(food : Food)
    }

    interface Presenter : BaseContract.BasePresenter {
        fun foodInput(cal : Int)
        fun searchFood(name: String)
        fun sendFoodInfo(food: Food)
        fun setPresenterAdapter(foodAdapter: FoodSearchActivity.FoodAdapter)
    }
}