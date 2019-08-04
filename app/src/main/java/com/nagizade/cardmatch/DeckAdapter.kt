package com.nagizade.cardmatch

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_layout.view.*
import java.util.*

class DeckAdapter(val items: ArrayList<Card>, val context: Context, val listener: (Int) -> Unit) : RecyclerView.Adapter<DeckAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Card,pos: Int,listener: (Int) -> Unit) = with(itemView) {
            setOnClickListener {
                listener(pos)
            }

            if(!item.isVisible) {
                card_image.setBackgroundColor(Color.parseColor("#ECDFBD"))
                card_image.setImageResource(R.drawable.ic_help)
            } else {
                card_image.setBackgroundColor(item.getBgColor())
                card_image.setImageResource(item.getImage())
            }
            if(item.isMatched) {
                card_image.visibility = View.INVISIBLE
                card_image.isEnabled = false
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position],position,listener)

    override fun getItemCount(): Int {
        return items.size
    }
}