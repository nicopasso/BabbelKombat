package com.nicopasso.babbelkombat.ui

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nicopasso.babbelkombat.R
import com.nicopasso.babbelkombat.model.Game
import kotlinx.android.synthetic.main.ranking_dialog_layout.*
import org.parceler.Parcels

class RankingDialog: DialogFragment() {

    companion object {
        val GAME = "game"
        val TAG = RankingDialog::class.java.canonicalName

        fun show(activity: AppCompatActivity, value: Game) {
            RankingDialog().apply {
                arguments = Bundle().apply {
                    putParcelable(GAME, Parcels.wrap(value))
                }
            }.show(activity.supportFragmentManager, TAG)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog.window.attributes.windowAnimations = R.style.DialogAnimation
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.ranking_dialog_layout, container, false)
        view?.setOnClickListener { _ -> }
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    fun initialize() {
        val game: Game = Parcels.unwrap(arguments.getParcelable(GAME))

        var wordsString = "WORDS:\n"
        for ((textEng, textSpa) in game.words) {
            wordsString += "$textEng / $textSpa \n"
        }

        words_textview.text = wordsString

        var ranking = "RANKING:\n"
        val sortedPlayers = game.players.sortedByDescending { it.score }
        for ((_, name, score) in sortedPlayers) {
            ranking += "$name: $score \n"
        }

        ranking_textview.text = ranking

        back_homepage_btn.setOnClickListener {
            dismiss()
            activity.finish()
        }

    }

}