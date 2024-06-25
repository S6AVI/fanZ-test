package com.saleem.data

import com.saleem.data.model.Player

val messi = Player(
    id = "player1",
    name = "Lionel Messi",
    teamId = "barcelona",
    number = "10",
    position = "Forward",
    photoUrl = "https://firebasestorage.googleapis.com/v0/b/fanz-test-7d215.appspot.com/o/neymar_photo.png?alt=media&token=56d40f82-0e33-44b1-a548-34bc815543c9",
    rating = 95.0,
    transferPrice = 150.0,
    cardType = "Free",
    country = "Argentina",
    rewards = 25.0
)



// Player 2
val cristiano = Player(
    id = "player2",
    name = "Cristiano Ronaldo",
    teamId = "Alnassar",
    number = "7",
    position = "Forward",
    photoUrl = "https://firebasestorage.googleapis.com/v0/b/fanz-test-7d215.appspot.com/o/cr7_photo.png?alt=media&token=2c73addd-0f2b-432c-a08a-2db3e65a7b04",
    rating = 92.0,
    transferPrice = 120.0,
    cardType = "Free",
    country = "Portugal",
    rewards = 20.0
)

// Player 3
val salah = Player(
    id = "player3",
    name = "Mo Salah",
    teamId = "psg",
    number = "7",
    position = "Forward",
    photoUrl = "https://firebasestorage.googleapis.com/v0/b/fanz-test-7d215.appspot.com/o/salah_photo.png?alt=media&token=046819d5-2f3f-4f5b-bda7-cb15ee9973c9",
    rating = 90.0,
    transferPrice = 130.0,
    cardType = "Free",
    country = "France",
    rewards = 22.0
)


val players = listOf(
    messi, messi, messi, messi, messi, messi, messi,
    messi, messi, messi,
    messi, messi, messi, cristiano, salah
)