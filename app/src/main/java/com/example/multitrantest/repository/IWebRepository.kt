package com.example.multitrantest.repository

import org.jsoup.select.Elements

interface IWebRepository {
    suspend fun getPage(lan1: Int, lan2: Int, cl: Int, word: String): Elements?
    
}