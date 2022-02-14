package com.geeksforgeeks.doubledownswipe

import android.os.Bundle
import android.view.MotionEvent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs


class MainActivity : AppCompatActivity() {

    private lateinit var mTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTextView = findViewById(R.id.text_view_1)
    }

    private val none = 0
    private val swipe = 1
    private var mode = none
    private var startY = 0f
    private var stopY = 0f
    private val threshold = 100

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_POINTER_DOWN -> {
                mode = swipe
                startY = event.getY(0).toInt().toFloat()
            }
            MotionEvent.ACTION_POINTER_UP -> {
                mode = none
                if (abs(startY - stopY) > threshold) {
                    if (startY > stopY) {
                        mTextView.text = "Swiped Up"
                    } else {
                        mTextView.text = "Swiped Down"
                    }
                }
                mode = none
            }
            MotionEvent.ACTION_MOVE -> if (mode == swipe) {
                stopY = event.getY(0)
            }
        }
        return true
    }
}