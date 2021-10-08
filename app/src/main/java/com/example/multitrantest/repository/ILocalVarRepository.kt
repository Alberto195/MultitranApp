package com.example.multitrantest.repository

interface ILocalVarRepository {
    var lan1: Int

    var lan2: Int

    var cl: Int

    var word: String

    var languageToId: Map<String, Int>

    var idtoLanguage: Map<Int, String>
}