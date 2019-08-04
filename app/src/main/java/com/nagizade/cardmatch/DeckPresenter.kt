package com.nagizade.cardmatch
import android.graphics.Color
import android.os.Handler
import com.nagizade.cardmatch.DeckInterface.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class DeckPresenter (view: View) : Presenter {

    private var mView: View = view
    var itemsCount = 12;
    var stepCount = 0;
    private var items: ArrayList<Card> = arrayListOf()
    private var images: ArrayList<CardImage> = arrayListOf()
    var visibles = arrayListOf<Int>()
    private var isCLickable = true // Variable to control RecyclerView elements clickability :)


    init {
        mView.bind()
        fillList()
    }

    override fun fillInitial() {
        var x = 0;
        while (x < itemsCount) {
            val image = images[Random.nextInt(images.size)]
            if (countOf(Card(image)) < 2) {
                items.add(Card(image))
                items.add(Card(image))
                x+=2
            }
        }
        // Shuffling items array to mix cards randomly
        items.shuffle()
    }

    override fun beginGame() {
        fillInitial()
        mView.startGame()
        stepCount = 0
    }

    override fun fillList() {
        images.add(CardImage(R.drawable.ic_beach,Color.parseColor("#FBE2B4")))
        images.add(CardImage(R.drawable.ic_cake,Color.parseColor("#6C8672")))
        images.add(CardImage(R.drawable.ic_cup,Color.parseColor("#FFD900")))
        images.add(CardImage(R.drawable.ic_flower,Color.parseColor("#638CA6")))
        images.add(CardImage(R.drawable.ic_local_bar,Color.parseColor("#260126")))
        images.add(CardImage(R.drawable.ic_palette,Color.parseColor("#ADC4CC")))
        images.add(CardImage(R.drawable.ic_airport,Color.parseColor("#354458")))
        images.add(CardImage(R.drawable.ic_pet,Color.parseColor("#1FDA9A")))
        images.add(CardImage(R.drawable.ic_school,Color.parseColor("#85C4B9")))
        images.add(CardImage(R.drawable.ic_traffic,Color.parseColor("#3B3A35")))
        images.add(CardImage(R.drawable.ic_world,Color.parseColor("#C6D5CD")))
        images.add(CardImage(R.drawable.ic_shipping,Color.parseColor("#69D2E7")))
    }

    override fun getAdapter(): DeckAdapter {
        var deckAdapter = DeckAdapter(items, mView.getContext()) {
            if (isCLickable) {
                if (!items[it].isVisible) {
                    items[it].isVisible = true
                    visibles.add(it)
                    mView.refreshData(it)
                    if (visibles.size == 2) {
                        isCLickable = false
                        var handler = Handler()
                        handler.postDelayed({
                            if (items[visibles[0]].getImage() == items[visibles[1]].getImage()) {
                                items[visibles[0]].isMatched = true
                                items[visibles[1]].isMatched = true
                                mView.showToast("Yay!")
                            } else {
                                items[visibles[0]].isVisible = false
                                items[visibles[1]].isVisible = false
                            }
                            mView.refreshData(visibles[0])
                            mView.refreshData(visibles[1])
                            visibles.clear()
                            isCLickable = true
                            checkGameEnd()
                        }, 1000L)
                        stepCount++
                    }
                    mView.updateSteps(stepCount)
                }
            }
        }
        return deckAdapter
    }

    fun checkGameEnd() {
        var i = 0
        for(k in 0 until items.size) {
            if(items[k].isMatched) i++
        }
        if(i == items.size) {
            mView.showEnding()
            items.clear()
        }
    }

    fun countOf(item: Card): Int {
        return Collections.frequency(items, item)
    }
}