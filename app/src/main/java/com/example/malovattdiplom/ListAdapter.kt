package com.example.malovattdiplom

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.ListFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.malovattdiplom.data.Item
import org.w3c.dom.Text

class ListAdapter(private val array: RowInterface, private val priceRefund: PriceRefund): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var itemList = emptyList<Item>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val id_item = itemView.findViewById<TextView>(R.id.id_item)
        val item_name_txt = itemView.findViewById<TextView>(R.id.item_name_txt)
        val item_vatt_count = itemView.findViewById<TextView>(R.id.item_vatt_count)
        val item_price_txt = itemView.findViewById<TextView>(R.id.item_price_txt)
        val rowLayout = itemView.findViewById<ConstraintLayout>(R.id.rowLayout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.id_item.text = itemList[position].id.toString()
        holder.item_name_txt.text = itemList[position].item_name
        holder.item_vatt_count.text = itemList[position].vatt_count.toString()
        holder.item_price_txt.text = (priceRefund.refund()*itemList[position].vatt_count).toString()
        holder.rowLayout.setOnClickListener{
            array.fun1(itemList[position].id.toString(), itemList[position].item_name, itemList[position].vatt_count.toString(), itemList[position].price.toString())

        }

    }

    override fun getItemCount(): Int {
        return itemList.size

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(item: List<Item>){
        this.itemList = item
        notifyDataSetChanged()
    }
}