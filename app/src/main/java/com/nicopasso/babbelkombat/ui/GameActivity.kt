package com.nicopasso.babbelkombat.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nicopasso.babbelkombat.R
import com.nicopasso.babbelkombat.model.Game
import com.nicopasso.babbelkombat.model.Player
import com.nicopasso.babbelkombat.model.Word
import com.nicopasso.babbelkombat.utils.BKUtils
import org.parceler.Parcels

class GameActivity: AppCompatActivity() {

    companion object {
        val GAME_WORDS_COUNT = 19
    }

    //Extras
    var words = emptyList<Word>()
    var players = emptyList<Player>()

    var game = Game(emptyList(), mutableListOf())
    var gameWords = mutableListOf<Word>()

    var selectedWordIndex = 0
    var selectedWord: Word? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        intent.extras.let {
            words = Parcels.unwrap(intent.getParcelableExtra("words"))
            players = Parcels.unwrap(intent.getParcelableExtra("players"))
        }

        game.players = players

        createGameWordsArray()
    }

    fun createGameWordsArray() {

        gameWords.clear()

        //Generate the array of words to use in the game
        for (i in 0..GAME_WORDS_COUNT) {
            val randomIndex = BKUtils().generateRandomNumber(words.count() - 1, 0)
            if (!gameWords.contains(words[randomIndex])) gameWords.add(words[randomIndex])
        }

        selectedWordIndex = BKUtils().generateRandomNumber(gameWords.count() - 1, 0)
        selectedWord = gameWords[selectedWordIndex]

        selectedWord?.let {
            game.words.add(it)

            //Add the selected word few times more in the game words array
            val step = BKUtils().generateRandomNumber(5, 1)
            for (i in 4..GAME_WORDS_COUNT step step) {
                gameWords.add(it)
            }
        }
    }


}