package com.example.multitrantest.repository

import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.lang.StringBuilder

class WebRepository(
) : IWebRepository {

    override suspend fun getPage(lan1: Int, lan2: Int, cl: Int, word: String): Elements? {
        val sb = StringBuilder(BASE_URL)
        sb.apply {
            append("?l1=")
            append(lan1)
            append("&l2=")
            append(lan2)
            append("&s=")
            append(word)
            append("&langlist=")
            append(cl)
        }
        val doc = Jsoup.connect(sb.toString()).get()

        return doc.select("td.trans")
    }

    companion object {
        private const val BASE_URL = "https://www.multitran.com/"
    }

}