package com.example.multitrantest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.multitrantest.R
import com.example.multitrantest.model.HistoryModel

class HistoryAdapter(
) : RecyclerView.Adapter<HistoryAdapter.HistoryHolder>() {

    private var translationsList = emptyList<HistoryModel>()

    fun setList(list: List<HistoryModel>?) {
        list?.let {
            translationsList = it
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_recycler_item, parent, false)
        return HistoryHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        holder.bind(position, translationsList)
    }

    override fun getItemCount(): Int = translationsList.size

    class HistoryHolder(
        view: View,
    ) : RecyclerView.ViewHolder(view) {

        private val word: TextView = view.findViewById(R.id.historyItemWord)
        private val lan1: TextView = view.findViewById(R.id.historyItemLan1)
        private val lan2: TextView = view.findViewById(R.id.historyItemLan2)


        fun bind(position: Int, transList: List<HistoryModel>) {
            word.text = transList[position].word
            lan1.text = transList[position].lan1
            lan2.text = transList[position].lan2
        }
    }
}
