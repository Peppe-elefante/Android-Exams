package com.example.esame5

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    val listLeft = mutableListOf("Carlo", "Maria", "Giovanni", "Marco")
    val listRight = mutableListOf("Mario", "Ilaria", "Leonard", "Paolo", "Vittoria")
    val listRand = mutableListOf("Giorgio", "Ciro", "Alan", "Adriana", "Riccardo", "Lilliana")

    var isMove = false

    private lateinit var tbToggle : ToggleButton
    private lateinit var lvLeft : ListView
    private lateinit var lvRight: ListView
    private lateinit var btnleft: Button
    private lateinit var btnright: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tbToggle = findViewById(R.id.toggleElement)
        tbToggle.setOnCheckedChangeListener({ _, isChecked ->
            isMove = isChecked
        })

        lvLeft = findViewById(R.id.list1)
        lvRight = findViewById(R.id.list2)

        setupListView(lvLeft, listLeft)
        setupListView(lvRight, listRight)

        btnleft = findViewById(R.id.insertLeft)
        btnright = findViewById(R.id.insertRight)

        setupButton(btnleft, lvLeft, listLeft)
        setupButton(btnright, lvRight, listRight)

    }

    private fun setupListView(listView: ListView, data: MutableList<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            if (isMove) {
                val item = data[position]
                val otherList = if (listView == lvLeft) listRight else listLeft
                otherList.add(item)
                data.removeAt(position)
                (listView.adapter as ArrayAdapter<String>).notifyDataSetChanged()
                (lvRight.adapter as ArrayAdapter<String>).notifyDataSetChanged()
            } else {
                data.removeAt(position)
                (listView.adapter as ArrayAdapter<String>).notifyDataSetChanged()
            }
        }
    }

    private fun setupButton(button: Button, listView: ListView, list : MutableList<String>) {
        button.setOnClickListener {
            list.add(listRand.random())
            (listView.adapter as ArrayAdapter<String>).notifyDataSetChanged()
        }
    }

}