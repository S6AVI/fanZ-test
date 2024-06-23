package com.saleem.fanz_test.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saleem.data.Player
import com.saleem.repo.CloudRepository
import com.saleem.util.UiState

class DetailsViewModel: ViewModel() {

    private val repository = CloudRepository()

    private val _player = MutableLiveData<UiState<Player>>()
    val player: LiveData<UiState<Player>>
        get() = _player



    fun getPlayer(playerId: String?) {
        repository.getPlayer(playerId) {
            _player.value = it
        }
    }

}
