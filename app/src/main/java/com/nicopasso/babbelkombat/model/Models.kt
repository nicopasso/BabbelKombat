package com.nicopasso.babbelkombat.model

import com.google.gson.annotations.SerializedName
import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel
data class Word @ParcelConstructor constructor(@SerializedName("text_eng") val textEng: String,
                                                       @SerializedName("text_spa") val textSpa: String)

@Parcel
data class Player @ParcelConstructor constructor(val id: Int, val name: String, var score: Int = 0)

@Parcel
data class Game @ParcelConstructor constructor(var players: List<Player>, var words: MutableList<Word>)