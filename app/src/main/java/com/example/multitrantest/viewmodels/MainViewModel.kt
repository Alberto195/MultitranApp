package com.example.multitrantest.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.multitrantest.database.DatabaseRepository
import com.example.multitrantest.database.DatabaseRepositoryImpl
import com.example.multitrantest.filter.FilterDiffLan
import com.example.multitrantest.filter.FilterSameLan
import com.example.multitrantest.filter.IFilter
import com.example.multitrantest.model.HistoryModel
import com.example.multitrantest.repository.ILocalVarRepository
import com.example.multitrantest.repository.IWebRepository
import com.example.multitrantest.repository.LocalVarRepository
import com.example.multitrantest.repository.WebRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.jsoup.select.Elements

class MainViewModel(
    application: Application,
    ): AndroidViewModel(application) {

    private val repo: IWebRepository = WebRepository()
    private val lRepo: ILocalVarRepository = LocalVarRepository()
    private val DBRepo: DatabaseRepository = DatabaseRepositoryImpl(application)
    private var filter: IFilter? = null

    private var _translations = MutableLiveData<List<String>>()
    val translations: LiveData<List<String>>
        get() = _translations

    private var _history = MutableLiveData<List<HistoryModel>>()
    val history: LiveData<List<HistoryModel>>
        get() = _history


    fun translate() {
        val listString = mutableListOf<String>()
        CoroutineScope(Dispatchers.IO).launch {
            val elements = repo.getPage(lRepo.lan1, lRepo.lan2, lRepo.cl, lRepo.word)

            filter = if(lRepo.lan1 == lRepo.lan2) FilterSameLan()
            else FilterDiffLan()

            val list = filter!!.cleanHTML(elements, lRepo.lan1)
            listString.addAll(list)
            postValueTranslations(listString)
            addSearchToHistory()
        }
    }

    fun setLan1(lan1: Int) {
        lRepo.lan1 = lan1
    }

    fun setLan2(lan2: Int) {
        lRepo.lan2 = lan2
    }

    fun setWord(word: String) {
        lRepo.word = word
    }

    fun getLanId(): Map<String, Int> {
        return lRepo.languageToId
    }

    private fun postValueTranslations(list: List<String>) {
        CoroutineScope(Dispatchers.Main).launch {
            _translations.value = list
        }
    }

    private fun addSearchToHistory() {
        CoroutineScope(Dispatchers.IO).launch {
            if (DBRepo.getHistory().size == 5) deleteTopSearchHistory()
            DBRepo.insertHistory(HistoryModel(
                word = lRepo.word,
                lan1 = lRepo.idtoLanguage[lRepo.lan1],
                lan2 = lRepo.idtoLanguage[lRepo.lan2]
            ))
        }
    }

    private fun deleteTopSearchHistory() {
        CoroutineScope(Dispatchers.IO).launch {
            val history = DBRepo.getHistory()
            DBRepo.deleteHistory(history[0])
        }
    }

    fun getHistory() {
        CoroutineScope(Dispatchers.IO).launch {
            val history = DBRepo.getHistory()
            CoroutineScope(Dispatchers.Main).launch {
                postValueHistory(history)
            }
        }
    }

    private fun postValueHistory(list: List<HistoryModel>) {
        _history.value = list
    }
}
