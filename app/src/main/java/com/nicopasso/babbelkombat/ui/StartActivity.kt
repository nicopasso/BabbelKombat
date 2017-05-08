package com.nicopasso.babbelkombat.ui

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.nicopasso.babbelkombat.BKApplication
import com.nicopasso.babbelkombat.R
import com.nicopasso.babbelkombat.di.StartActivityModule
import kotlinx.android.synthetic.main.activity_start.*
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
    }
    //endregion
}
