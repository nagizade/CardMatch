package com.nagizade.cardmatch

data class Card(val cardImage: CardImage,var isVisible: Boolean = false,var isMatched: Boolean = false) {
    fun getImage() : Int {
        return cardImage.image
    }

    fun getBgColor() : Int {
        return cardImage.backgroundColor
    }
}

data class CardImage(val image: Int, val backgroundColor: Int)