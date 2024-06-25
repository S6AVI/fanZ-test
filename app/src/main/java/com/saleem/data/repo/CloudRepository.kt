package com.saleem.data.repo

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.saleem.data.model.Player
import com.saleem.data.players
import com.saleem.util.UiState

class CloudRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val playersCollectionRef = firestore.collection("players")

    fun getPlayers(result: (UiState<List<Player>>) -> Unit) {
        playersCollectionRef.get()
            .addOnSuccessListener { querySnapshot ->
                val playersList = mutableListOf<Player>()
                for (document in querySnapshot.documents) {
                    val player = document.toObject(Player::class.java)
                    player?.id = document.id
                    playersList.add(player!!)
                }
                result(UiState.Success(playersList))
            }
            .addOnFailureListener { exception ->
                result(UiState.Failure(exception.message ?: "Error fetching players"))
            }
    }

    fun uploadPlayers() {
        for (player in players) {
            playersCollectionRef.add(player)
                .addOnSuccessListener { documentReference ->
                    val playerId = documentReference.id
                    player.id = playerId
                }
                .addOnFailureListener { exception ->
                    Log.e("savii", "Error uploading player", exception)
                }
        }
    }

    fun getPlayer(playerId: String?, result: (UiState<Player>) -> Unit) {
        if (playerId == null) {
            result(UiState.Failure("Player ID is null"))
            return
        }

        playersCollectionRef.document(playerId)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val player = documentSnapshot.toObject(Player::class.java)
                    player?.id = documentSnapshot.id
                    result(UiState.Success(player!!))
                } else {
                    result(UiState.Failure("Player not found"))
                }
            }
            .addOnFailureListener { exception ->
                result(UiState.Failure(exception.message ?: "Error fetching player"))
            }
    }


}