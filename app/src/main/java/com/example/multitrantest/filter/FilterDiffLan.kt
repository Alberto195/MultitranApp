package com.example.multitrantest.filter

import org.jsoup.select.Elements

class FilterDiffLan: IFilter {

    override fun cleanHTML(elements: Elements?, l1: Int): List<String> {
        val arrayString = mutableListOf<String>()
        elements?.forEach {
            val string = it.toString()
            val start = string.indexOf("l2=$l1\">")
            val end = string.indexOf("</a>")
            val word = string.subSequence(start+6, end)
            if (start != -1) arrayString.add(word.toString())
        }

        return arrayString.toList()
    }
}