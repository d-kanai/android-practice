package com.example.android_practice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

const val EXTRA_MESSAGE = "message"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.roll_button)
        Log.v("APP_LOG", "Hello, world!")
        diceRoll()
        rollButton.setOnClickListener {
            Log.v("APP_LOG", "dice roll")
            diceRoll()
        }
    }

    private fun diceRoll() {
        val resultTextView: TextView = (findViewById(R.id.roll_text))
        val diceValue = Dice(6).roll()
        resultTextView.text = diceValue.toString()
        val diceImage: ImageView = findViewById(R.id.dice_image)
        val drawableResource = when (diceValue) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        diceImage.setImageResource(drawableResource)
        Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT).show()
    }

    fun sendMessage(view: View) {
        println("click button")
        val editText = findViewById<EditText>(R.id.editTextTextPersonName)
        val message = editText.text.toString()
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }
}

class Dice(private val numSides: Int) {

    fun roll(): Int {
        return (1..numSides).random()
    }
}