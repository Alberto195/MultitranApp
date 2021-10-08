package com.example.multitrantest.filter

import org.jsoup.select.Elements

class FilterSameLan: IFilter {

    override fun cleanHTML(elements: Elements?, l1: Int): List<String> {
        val arrayString = mutableListOf<String>()
        elements?.forEach {
            val string = it.toString()
            val beginning = string.indexOf(">")

            val word: CharSequence = if(string.contains("span")) {
                val ending = string.indexOf("<span")
                string.subSequence(beginning+1, ending)

            } else {
                val ending = string.indexOf("</")
                string.subSequence(beginning+1, ending)
            }

            arrayString.add(word.toString())
        }

        return arrayString.toList()
    }
}