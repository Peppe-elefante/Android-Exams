package com.example.esame1

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class ScoreAdapter (
    private val context: Activity,
    var scores: List<Score>
        ) : ArrayAdapter<Score>(context, R.layout.player_scoreboard, scores) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.player_scoreboard, null)

        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvScore : TextView = view.findViewById(R.id.tvScore)

        tvName.text = scores[position].name
        tvScore.text = (scores[position].score).toString()

        return view
    }
}