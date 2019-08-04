package com.nagizade.cardmatch

import android.content.Context

interface DeckInterface {

    interface Presenter {
        fun fillInitial()
        fun fillList()
        fun getAdapter() : DeckAdapter
        fun beginGame()
    }

    interface View {
        fun bind()
        fun getContext() : Context
        fun refreshData(position: Int)
        fun showToast(message: String)
        fun updateSteps(value: Int)
        fun showEnding()
        fun startGame()
    }
}