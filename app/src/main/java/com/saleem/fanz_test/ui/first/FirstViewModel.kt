package com.saleem.fanz_test.ui.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.saleem.data.model.Player
import com.saleem.data.repo.CloudRepository
import com.saleem.util.UiState
import com.saleem.util.logD
import kotlinx.coroutines.launch

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

    private val remoteConfig = FirebaseRemoteConfig.getInstance()
    private val configSettings = FirebaseRemoteConfigSettings.Builder()
        .setMinimumFetchIntervalInSeconds(3600)
        .build()

    init {
        remoteConfig.setConfigSettingsAsync(configSettings)
    }

    fun fetchAndActivate() {
        viewModelScope.launch {
            try {
                remoteConfig.fetchAndActivate()

            } catch (e: Exception) {
               logD("error in fetchAndActivate: ${e.message}")
            }
        }
    }

    fun getInt(key: String): Int {
        return remoteConfig.getLong(key).toInt()
    }

    // to upload data to firebase initially, runs only once, then comment it out
//    fun uploadPlayers() {
//        repository.uploadPlayers()
//    }
}
