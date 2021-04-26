package com.example.hw2_hannahjuarez

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

class SortingAndSearchingActivity : AppCompatActivity() {
    private val arrayA: Array<Int?> = arrayOfNulls<Int>(20)
    private val arrayB: Array<Int?> = arrayOfNulls<Int>(10)
    private val arrayS: Array<String?> = arrayOfNulls<String>(10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sorting_and_searching)

        findViewById<Button>(R.id.button_generate).setOnClickListener {
            var textA: String = ""
            var textB: String = ""
            var textS: String = ""

            for (i in 1..10 step 1) {
                arrayA[i - 1] = i - 1
                textA += arrayA[i - 1].toString()
                if (i < 10) textA += ", "

                arrayB[arrayB.size - i] = arrayB.size - i
                textB += arrayB[arrayB.size - i].toString()
                if (i < 10) textB += ", "

                arrayS[i - 1] = (64 + i).toChar().toString()
                textS += arrayS[i - 1]
                if (i < 10) textS += ", "
            }

            findViewById<TextView>(R.id.text_view_displayA).text = textA
            findViewById<TextView>(R.id.text_view_displayB).text = textB
            findViewById<TextView>(R.id.text_view_displayS).text = textS
        }

        findViewById<Button>(R.id.button_merge).setOnClickListener {
            var textA: String = ""
            var textB: String = ""

            merge(arrayA, arrayB, 9, 9)
            for (i in 1..20 step 1) {
                textA += arrayA[i - 1].toString()
                if (i < 20) textA += ", "

                if (i <= 10) {
                    textB += arrayB[arrayB.size - i].toString()
                    if (i < 10) textB += ", "
                }
            }
            findViewById<TextView>(R.id.text_view_displayA).text = textA
            findViewById<TextView>(R.id.text_view_displayB).text = textB
        }

        findViewById<Button>(R.id.button_search).setOnClickListener {
            val str: String = findViewById<EditText>(R.id.input_search_value).text.toString()
            val result: Int = search(arrayS, str)
            Log.d("search result", result.toString())
            val view: TextView = findViewById<TextView>(R.id.text_view_results)

            if (result == -1)
                view.text = "Oops! Couldn't find $str"
            else
                view.text = "Located at $result"
        }

        findViewById<Button>(R.id.button_back).setOnClickListener { this.finish() }
    }

    private fun searchR(strings: Array<String?>, str: String, first: Int, last: Int): Int {
        if (first > last) return -1
        Log.d("first > last", "true")

        // Move mid to the middle
        var mid: Int = (first + last) / 2

        // If mid is empty, find closest non-empty string
        if (strings[mid]!!.isEmpty()) {
            var left: Int = mid - 1
            var right: Int = mid + 1
            while (true) {
                if (left < first && right > last) return -1
                else if (right <= last && strings[right]!!.isNotEmpty()) {
                    mid = right
                    break
                } else if (left >= first && strings[left]!!.isNotEmpty()) {
                    mid = left
                    break
                }
                right++
                left--
            }
        }

        // Check for string, and recurse if necessary
        return when {
            str == strings[mid] -> mid
            strings[mid]!! < str -> searchR(strings, str, mid + 1, last)
            else -> searchR(strings, str, first, mid - 1)
        }
    }

    // search() requires that the array elements be sorted from least to
    // greatest otherwise it will fail to search properly.
    private fun search(strings: Array<String?>, str: String): Int {
        if (str == "") return -1
        return searchR(strings, str, 0, strings.size - 1)
    }

    private fun merge(a: Array<Int?>, b: Array<Int?>, lastA: Int, lastB: Int) {
        var indexA: Int = lastA - 1 // Index of last element in array a
        var indexB: Int = lastB - 1 // Index of last element in array b
        var indexMerged: Int = lastA + lastB + 1 // End of merged array

        // Merge a and b, starting from the last element in each
        while (indexA >= 0 && indexB >= 0) {
            // End of a is > than end of B
            if (a[indexA]!! > b[indexB]!!) {
                a[indexMerged] = a[indexA] // copy element
                indexMerged--
                indexA--
            } else {
                a[indexMerged] = b[indexB] // copy element
                indexMerged--
                indexB--
            }
        }
    }
}