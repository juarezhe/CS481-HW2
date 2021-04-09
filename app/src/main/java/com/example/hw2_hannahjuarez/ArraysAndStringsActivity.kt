package com.example.hw2_hannahjuarez

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView

class ArraysAndStringsActivity : AppCompatActivity() {
    private var arrayList = ArrayList<TextView>()
    private var head = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arrays_and_strings)

        val windowWidth =
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                    windowManager.maximumWindowMetrics.bounds.width() - 32
                } else {
                    TODO("VERSION.SDK_INT < R")
                }

        val gridLayout: GridLayout = findViewById(R.id.grid_layout)

        for (i in head..((head + 15) % 16) step 1) {
            val cell: TextView = TextView(this)
            when (i) {
                in 0..2 -> cell.setBackgroundColor(Color.MAGENTA)
                in 3..5 -> cell.setBackgroundColor(Color.BLUE)
                in 6..8 -> cell.setBackgroundColor(Color.GREEN)
                in 9..11 -> cell.setBackgroundColor(Color.WHITE)
                else -> cell.setBackgroundColor(Color.DKGRAY)
            }
            cell.width = (windowWidth - 129) / 4
            cell.height = (windowWidth - 129) / 4
            cell.gravity = Gravity.CENTER
            cell.text = i.toString()

            arrayList.add(cell)
        }

        displayGrid(arrayList, gridLayout)

        findViewById<Button>(R.id.button_rotate).setOnClickListener { rotate(arrayList, gridLayout) }
        findViewById<Button>(R.id.button_back).setOnClickListener { this.finish() }
    }

    private fun rotate(arrayList: ArrayList<TextView>, gridLayout: GridLayout) {
        head = (head + 9) % 12
        displayGrid(arrayList, gridLayout)
    }

    private fun displayGrid(arrayList: ArrayList<TextView>, gridLayout: GridLayout) {
        gridLayout.removeAllViews()

        for (i in ((head + 0) % 12)..((head + 2) % 12) step 1) gridLayout.addView(arrayList[i])
        gridLayout.addView(arrayList[(head + 3) % 12])

        gridLayout.addView(arrayList[(head + 11) % 12])
        for (i in 12..13 step 1) gridLayout.addView(arrayList[i])
        gridLayout.addView(arrayList[(head + 4) % 12])

        gridLayout.addView(arrayList[(head + 10) % 12])
        for (i in 14..15 step 1) gridLayout.addView(arrayList[i])
        gridLayout.addView(arrayList[(head + 5) % 12])

        gridLayout.addView(arrayList[(head + 9) % 12])
        for (i in ((head + 8) % 12) downTo ((head + 6) % 12) step 1) gridLayout.addView(arrayList[i])
    }
}