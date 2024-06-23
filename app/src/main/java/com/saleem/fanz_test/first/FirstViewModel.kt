package com.saleem.fanz_test.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saleem.data.Player
import com.saleem.repo.CloudRepository
import com.saleem.util.UiState

class FirstViewModel: ViewModel() {


    private val repository = CloudRepository()

    private val _players = MutableLiveData<UiState<List<Player>>>()
    val players: LiveData<UiState<List<Player>>>
        get() = _players

    fun getLineup() {
        _players.value = UiState.Loading
        repository.getPlayers {
            _players.value = it
        }

    }

    // to upload data to firebase initially, runs only once, then comment it out
//    fun uploadPlayers() {
//        repository.uploadPlayers()
//    }
}
