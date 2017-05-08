package com.nicopasso.babbelkombat.ui

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nicopasso.babbelkombat.R

class PlayersDialog: DialogFragment() {

    companion object {
        val NUM_PLAYERS = "num_players"
        val TAG = PlayersDialog::class.java.canonicalName

        fun show(activity: AppCompatActivity, value: Int) {
            PlayersDialog().apply {
                arguments = Bundle().apply {
                    putInt(NUM_PLAYERS, value)
                }
            }.show(activity.supportFragmentManager, TAG)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog.window.attributes.windowAnimations = R.style.DialogAnimation
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.players_dialog_layout, container, false)
        view?.setOnClickListener { _ -> }
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    fun initialize() {

    }
}