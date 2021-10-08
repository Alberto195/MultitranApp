package com.example.multitrantest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.multitrantest.R

class TranslationsAdapter(
) : RecyclerView.Adapter<TranslationsAdapter.TranslationHolder>() {

    private var translationsList = emptyList<String>()

    fun setList(list: List<String>?) {
        list?.let {
            translationsList = it
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranslationHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return TranslationHolder(view)
    }

    override fun onBindViewHolder(holder: TranslationHolder, position: Int) {
        holder.bind(position, translationsList)
    }

    override fun getItemCount(): Int = translationsList.size

    class TranslationHolder(
        view: View,
    ) : RecyclerView.ViewHolder(view) {

        private val number: TextView = view.findViewById(R.id.number)
        private val translation: TextView = view.findViewById(R.id.translation)

        fun bind(position: Int, transList: List<String>) {
            number.text = (position+1).toString()
            translation.text = transList[position]
        }
    }
}
