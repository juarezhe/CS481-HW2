package com.example.hw2_hannahjuarez

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text
import java.lang.Exception

class StacksAndQueuesActivity : AppCompatActivity() {
    private val stackSize: Int = 100
    private var buffer: Array<Int?> = arrayOfNulls<Int>(stackSize * 3)
    private val stackPointer: Array<Int> = arrayOf(-1, -1, -1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stacks_and_queues)

        findViewById<Button>(R.id.button_push).setOnClickListener {
            try {
                push(0, findViewById<EditText>(R.id.input_push).text.toString().toInt())
            } catch (e: Exception) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        findViewById<Button>(R.id.button_pop).setOnClickListener {
            try {
                findViewById<TextView>(R.id.text_view_pop).text = pop(0).toString()
            } catch (e: Exception) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        findViewById<Button>(R.id.button_peek).setOnClickListener {
            try {
                findViewById<TextView>(R.id.text_view_peek).text = peek(0).toString()
            } catch (e: Exception) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        findViewById<Button>(R.id.button_back).setOnClickListener { this.finish() }
    }

    private fun push(stackNum: Int, value: Int) {
        if (stackPointer[stackNum] + 1 >= stackSize) throw Exception("Out of space.")
        stackPointer[stackNum]++
        buffer[absTopOfStack(stackNum)] = value
    }

    private fun pop(stackNum: Int): Int? {
        if (stackPointer[stackNum] == -1) throw Exception("Trying to pop an empty stack.")
        val value = buffer[absTopOfStack(stackNum)]
        buffer[absTopOfStack(stackNum)] = null
        stackPointer[stackNum]--;
        return value
    }

    private fun peek(stackNum: Int): Int? = buffer[absTopOfStack(stackNum)]

    fun isEmpty(stackNum: Int): Boolean = stackPointer[stackNum] == -1

    private fun absTopOfStack(stackNum: Int): Int = stackNum * stackSize + stackPointer[stackNum]
}