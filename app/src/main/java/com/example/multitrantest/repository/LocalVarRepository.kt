package com.example.multitrantest.repository

class LocalVarRepository(
) : ILocalVarRepository {
    override var lan1: Int = 1
    override var lan2: Int = 1
    override var cl: Int = 1
    override var word: String = ""

    override var languageToId: Map<String, Int> =
        mapOf("Русский" to 2, "Английский" to 1, "Немецкий" to 3, "Испанский" to 5)

    override var idtoLanguage: Map<Int, String> =
        mapOf(2 to "Русский", 1 to "Английский", 3 to "Немецкий", 5 to "Испанский")
}