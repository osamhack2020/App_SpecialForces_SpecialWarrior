package co.specialforce.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, VH: BaseViewHolder>(private val items: MutableList<T>): RecyclerView.Adapter<VH>() {
    open fun setNewItems(newItems: List<T>){
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size
}