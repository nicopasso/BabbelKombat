package com.nicopasso.babbelkombat.ui

import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.Button
import com.nicopasso.babbelkombat.R
import com.nicopasso.babbelkombat.model.Game
import com.nicopasso.babbelkombat.model.Player
import com.nicopasso.babbelkombat.model.Word
import com.nicopasso.babbelkombat.ui.StartActivity.Companion.TEXT_FONT
import com.nicopasso.babbelkombat.utils.BKUtils
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.player_layout.view.*
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
    var roundNumber = 1

    var mpRight: MediaPlayer? = null
    var mpWrong: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        intent.extras.let {
            words = Parcels.unwrap(intent.getParcelableExtra("words"))
            players = Parcels.unwrap(intent.getParcelableExtra("players"))
        }

        game.players = players

        mpRight = MediaPlayer.create(this, R.raw.cheer)
        mpWrong = MediaPlayer.create(this, R.raw.cwdboo)

        createGameWordsArray()
        initUI()
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
                gameWords.add(i, it)
            }
        }
    }

    fun initUI() {

        round_textview.text = resources.getString(R.string.round_number, roundNumber)

        word_to_guess_textview.text = selectedWord?.textEng

        play_btn.apply {
            applyFont(TEXT_FONT)
            setOnClickListener {
                round_container_layout.visibility = View.GONE
                words_container_layout.visibility = View.VISIBLE
                startGame()
            }
        }

        player_one.arcade_btn.tag = 1
        player_two.arcade_btn.tag = 2
        player_three.arcade_btn.tag = 3
        player_four.arcade_btn.tag = 4

        when(players.count()) {
            1 -> {
                player_two.visibility = View.GONE
                player_three.visibility = View.GONE
                player_four.visibility = View.GONE
                player_one.player_name_textview.text = players[0].name
            }
            2 -> {
                player_three.visibility = View.GONE
                player_four.visibility = View.GONE
                player_one.player_name_textview.text = players[0].name
                player_two.player_name_textview.text = players[1].name
            }
            3 -> {
                player_four.visibility = View.GONE
                player_one.player_name_textview.text = players[0].name
                player_two.player_name_textview.text = players[1].name
                player_three.player_name_textview.text = players[2].name
            }
            4 -> {
                player_one.player_name_textview.text = players[0].name
                player_two.player_name_textview.text = players[1].name
                player_three.player_name_textview.text = players[2].name
                player_four.player_name_textview.text = players[3].name
            }
        }

    }

    fun startGame() {

        var wordIndex = 0

        animated_textview.text = gameWords[wordIndex].textSpa

        val animation = TranslateAnimation(Animation.RELATIVE_TO_PARENT, -1.5f,
                Animation.RELATIVE_TO_PARENT, 1f,
                Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_PARENT, 0f)
        animation.duration = 3500
        animation.startOffset = 100
        animation.repeatCount = Animation.INFINITE
        animation.interpolator = AccelerateDecelerateInterpolator()

        animation.setAnimationListener(object: Animation.AnimationListener {

            override fun onAnimationStart(animation: Animation?) {
                animated_textview.visibility = View.VISIBLE
            }

            override fun onAnimationRepeat(animation: Animation?) {
                wordIndex++
                if (wordIndex == gameWords.count()) { //no correct word guess => new Round
                    createGameWordsArray()
                    updateUI()
                } else {
                    animated_textview.text = gameWords[wordIndex].textSpa
                }
            }

            override fun onAnimationEnd(animation: Animation?) {
                animated_textview.visibility = View.GONE
            }
        })

        animated_textview.startAnimation(animation)

        player_one.arcade_btn.setOnClickListener {
            v -> playerButtonAction((v as Button), animation)
        }
        player_two.arcade_btn.setOnClickListener {
            v -> playerButtonAction((v as Button), animation)
        }
        player_three.arcade_btn.setOnClickListener {
            v -> playerButtonAction((v as Button), animation)
        }
        player_four.arcade_btn.setOnClickListener {
            v -> playerButtonAction((v as Button), animation)
        }
    }

    fun playerButtonAction(button: Button, animation: TranslateAnimation) {

        if (selectedWord?.textSpa == animated_textview.text) {
            mpRight?.start()
            mpRight?.setOnCompletionListener {
                if (roundNumber >= 2) {
                    RankingDialog.show(this, game)
                } else {
                    createGameWordsArray()
                    updateUI()
                }
            }
            animation.cancel()

            players.filter {
                button.tag == it.id
            }.forEach {
                it.score += 100
            }
        } else {
            mpWrong?.start()
        }

    }

    fun updateUI() {
        roundNumber++
        round_textview.text = resources.getString(R.string.round_number, roundNumber)

        word_to_guess_textview.text = selectedWord?.textEng

        round_container_layout.visibility = View.VISIBLE
        words_container_layout.visibility = View.GONE

        for (player in players) {
            when(player.id) {
                1 -> player_one.player_score_textview.text = player.score.toString()
                2 -> player_two.player_score_textview.text = player.score.toString()
                3 -> player_three.player_score_textview.text = player.score.toString()
                4 -> player_four.player_score_textview.text = player.score.toString()
            }

        }
    }

}