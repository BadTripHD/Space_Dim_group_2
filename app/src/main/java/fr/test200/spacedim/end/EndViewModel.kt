package fr.test200.spacedim.end

import android.content.Context
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import fr.test200.spacedim.R

class EndViewModel(finalScore: Int, win: Boolean)  : ViewModel() {

    //region Data
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _win = MutableLiveData<Boolean>()
    val win: LiveData<Boolean>
        get() = _win
    //endregion

    //region Text
    private val _winText = MutableLiveData<String>()
    val winText: LiveData<String>
        get() = _winText

    private val _reputationText = MutableLiveData<String>()
    val reputationText: LiveData<String>
        get() = _reputationText
    //endregion

    //region Event
    private val _eventPlayAgain = MutableLiveData<Boolean>()
    val eventPlayAgain: LiveData<Boolean>
        get() = _eventPlayAgain

    private val _eventHighScore = MutableLiveData<Boolean>()
    val eventHighScore: LiveData<Boolean>
        get() = _eventHighScore
    //endregion

    init {
        _score.value = finalScore
        _win.value = win
    }

    /**
     * Callback called when the ViewModel is destroyed
     */
    override fun onCleared() {
        super.onCleared()
    }

    fun setWinText(text: String) {
        _winText.value = text
    }

    fun setReputationText(text: String) {
        _reputationText.value = text
    }

    fun onPlayAgain() {
        _eventPlayAgain.value = true
    }
    fun onPlayAgainComplete() {
        _eventPlayAgain.value = false
    }

    fun onGoHighScore() {
        _eventHighScore.value = true
    }
    fun onGoHighScoreComplete() {
        _eventHighScore.value = false
    }

}