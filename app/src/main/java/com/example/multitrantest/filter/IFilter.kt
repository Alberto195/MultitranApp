package com.example.multitrantest.filter

import org.jsoup.select.Elements


interface IFilter {

    fun cleanHTML(elements: Elements?, l1: Int): List<String>
}