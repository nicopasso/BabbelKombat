package com.nicopasso.babbelkombat.ui

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.nicopasso.babbelkombat.BKApplication
import com.nicopasso.babbelkombat.R
import com.nicopasso.babbelkombat.di.StartActivityModule
import com.nicopasso.babbelkombat.model.Player
import com.nicopasso.babbelkombat.model.Word
import kotlinx.android.synthetic.main.activity_start.*
import org.parceler.Parcels
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

fun Context.typefaceFromAssets(assetPath: String): Typeface =
    Typeface.createFromAsset(assets, assetPath)

fun TextView.applyFont(assetPath: String) {
    typeface = context.typefaceFromAssets(assetPath)
}

class StartActivity : AppCompatActivity(), StartView {

    @Inject lateinit var presenter: StartActivityPresenterImpl

    val AppCompatActivity.app: BKApplication
        get() = application as BKApplication

    val component by lazy {
        app.component.plus(StartActivityModule(this))
    }

    val mWords = mutableListOf<Word>()

    companion object {
        val TITLE_FONT = "fonts/mk5style.ttf"
        val TEXT_FONT = "fonts/mk1.ttf"
    }

    //region Activity lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        component.inject(this)
        presenter.attachView(this)

        title_textview.applyFont(TITLE_FONT)
        one_player_btn.applyFont(TEXT_FONT)
        two_players_btn.applyFont(TEXT_FONT)
        three_players_btn.applyFont(TEXT_FONT)
        four_players_btn.applyFont(TEXT_FONT)

        presenter.loadWords(this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    word -> mWords.add(word)
                }

        one_player_btn.setOnClickListener { v -> buttonClicked(v)}
        two_players_btn.setOnClickListener { v -> buttonClicked(v)}
        three_players_btn.setOnClickListener { v -> buttonClicked(v)}
        four_players_btn.setOnClickListener { v -> buttonClicked(v)}
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
    //endregion

    fun buttonClicked(view: View) {
        val numberOfPlayers = (view as Button).text.toString().toInt()
        val players = mutableListOf<Player>()

        (1..numberOfPlayers).map {
            index -> players.add(Player(index, "Player $index"))
        }

        val intent = Intent(this, GameActivity::class.java)
        val parcelablePlayers = Parcels.wrap(players)
        val parcelableWords = Parcels.wrap(mWords)
        intent.putExtra("players", parcelablePlayers)
        intent.putExtra("words", parcelableWords)
        startActivity(intent)

    }
}
