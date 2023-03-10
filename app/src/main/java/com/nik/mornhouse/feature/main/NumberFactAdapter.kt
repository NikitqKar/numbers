package com.nik.mornhouse.feature.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nik.mornhouse.R
import com.nik.mornhouse.data.entity.NumberFact

class NumberFactAdapter(private val onItemClickListener: (NumberFact) -> Unit) :
    RecyclerView.Adapter<NumberFactAdapter.ViewHolder>() {

    private val items = mutableListOf<NumberFact>()

    fun setItems(newItems: List<NumberFact>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemClickListener(item)
        }
    }

    override fun getItemCount(): Int = items.size


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val numberTextView: TextView = view.findViewById(R.id.number_textview)
        private val factTextView: TextView = view.findViewById(R.id.fact_textview)

        fun bind(item: NumberFact) {
            numberTextView.text = item.number.toString()
            factTextView.text = item.text
            factTextView.maxLines = 1
        }
    }
}
