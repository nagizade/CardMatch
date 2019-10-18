package com.nagizade.cardmatch

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.nagizade.cardmatch.DeckInterface.View
import com.nagizade.cardmatch.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(),View {

    private lateinit var presenter : DeckPresenter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        presenter = DeckPresenter(this)
        presenter.beginGame()
    }

    override fun getContext(): Context {
        return this
    }

    override fun startGame() {
        binding.stepCounterText.text = "0"

        binding.deckRecycler.visibility     = android.view.View.VISIBLE
        binding.stepCounterText.visibility  = android.view.View.VISIBLE
        binding.stepsText.visibility        = android.view.View.VISIBLE
        binding.endingLayout.visibility     = android.view.View.GONE

        binding.deckRecycler.layoutManager = GridLayoutManager(this, 3)
        binding.deckRecycler.adapter = presenter.getAdapter()
    }

    override fun showEnding() {
        binding.deckRecycler.visibility           = android.view.View.GONE
        binding.stepCounterText.visibility      = android.view.View.GONE
        binding.stepsText.visibility = android.view.View.GONE
        binding.endingLayout.visibility = android.view.View.VISIBLE
    }

    override fun refreshData(position: Int) {
        binding.deckRecycler.adapter?.notifyItemChanged(position)
    }

    override fun showToast(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun updateSteps(value: Int) {
        binding.stepCounterText.text = value.toString()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun bind() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = ""

        binding.playagainButton.setOnClickListener {
            presenter.beginGame()
        }
    }


}