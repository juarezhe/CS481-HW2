package com.example.hw2_hannahjuarez

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import kotlin.math.abs


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_arrays_and_strings).setOnClickListener {
            startActivity(
                Intent(this, ArraysAndStringsActivity::class.java)
            )
        }
        findViewById<Button>(R.id.button_stacks_and_queues).setOnClickListener {
            startActivity(
                Intent(this, StacksAndQueuesActivity::class.java)
            )
        }
        findViewById<Button>(R.id.button_sorting_and_searching).setOnClickListener {
            startActivity(
                Intent(this, SortingAndSearchingActivity::class.java)
            )
        }
    }
}
