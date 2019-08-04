package com.nagizade.cardmatch

import android.content.Context
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.MotionEvent
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nagizade.cardmatch.DeckInterface.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),View {

    lateinit var rv: RecyclerView;
    lateinit var tvSteps: TextView
    lateinit var tvStepsLabel: TextView
    lateinit var endingLayout: ConstraintLayout
    lateinit var playAgainButton: Button
    private lateinit var presenter : DeckPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = DeckPresenter(this)
        presenter.beginGame()
    }

    override fun getContext(): Context {
        return this
    }

    override fun startGame() {
        tvSteps.text = "0"

        rv.visibility           = android.view.View.VISIBLE
        tvSteps.visibility      = android.view.View.VISIBLE
        tvStepsLabel.visibility = android.view.View.VISIBLE
        endingLayout.visibility = android.view.View.GONE

        rv.layoutManager = GridLayoutManager(this, 3)
        rv.adapter = presenter.getAdapter()
    }

    override fun showEnding() {
        rv.visibility           = android.view.View.GONE
        tvSteps.visibility      = android.view.View.GONE
        tvStepsLabel.visibility = android.view.View.GONE
        endingLayout.visibility = android.view.View.VISIBLE
    }

    override fun refreshData(position: Int) {
        rv.adapter?.notifyItemChanged(position)
    }

    override fun showToast(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun updateSteps(value: Int) {
        tvSteps.text = value.toString()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun bind() {
        rv = findViewById(R.id.deck_recycler)
        tvSteps = findViewById(R.id.stepCounter_text)
        tvStepsLabel = findViewById(R.id.steps_text)
        endingLayout = findViewById(R.id.ending_layout)
        playAgainButton = findViewById(R.id.playagain_button)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = ""

        playAgainButton.setOnClickListener {
            presenter.beginGame()
        }
    }


}