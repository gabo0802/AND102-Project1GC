package com.example.project1wordle

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.project1wordle.FourLetterWordList
import com.example.project1wordle.FourLetterWordList.getRandomFourLetterWord
import com.example.project1wordle.MainActivity.State.*

class MainActivity : AppCompatActivity() {

    enum class State {
        guess0, guess1, guess2, guess3
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var wordToGuess = getRandomFourLetterWord()

        val title = findViewById<TextView>(R.id.textView)
        val answer = findViewById<TextView>(R.id.Answer)
        answer.text = wordToGuess
        val Guess1 = findViewById<TextView>(R.id.Guess1B)
        val Guess1Check = findViewById<TextView>(R.id.Guess1CheckB)
        val Guess2 = findViewById<TextView>(R.id.Guess2B)
        val Guess2Check = findViewById<TextView>(R.id.Guess2CheckB)
        val Guess3 = findViewById<TextView>(R.id.Guess3B)
        val Guess3Check = findViewById<TextView>(R.id.Guess3CheckB)
        val guessButton = findViewById<TextView>(R.id.button)
        val guessText = findViewById<TextView>(R.id.inputText)
        val resetButton = findViewById<TextView>(R.id.buttonReset)

        var curState = guess0
        println(answer.text.toString())

        guessButton.setOnClickListener{

            when (curState) {

                guess0 -> {
                    Guess1.text =guessText.text
                    Guess1Check.text = checkGuess(Guess1.text.toString(), wordToGuess)
                    curState = guess1
                    if(Guess1Check.text == "OOOO") {
                        title.text = "Congratulations!"
                        title.setTextColor(Color.parseColor("#0F9D58"))
                        curState = guess3
                        answer.visibility = View.VISIBLE
                    }
                }
                guess1 -> {
                    Guess2.text = guessText.text
                    Guess2Check.text = checkGuess(Guess2.text.toString(), wordToGuess)
                    curState = guess2
                    if(Guess2Check.text == "OOOO") {
                        title.text = "Congratulations!"
                        title.setTextColor(Color.parseColor("#0F9D58"))
                        curState = guess3
                        answer.visibility = View.VISIBLE
                    }
                }
                guess2 -> {
                    Guess3.text = guessText.text
                    Guess3Check.text = checkGuess(Guess3.text.toString(), wordToGuess)
                    curState = guess3
                    answer.visibility = View.VISIBLE

                    if(Guess3Check.text == "OOOO") {
                        title.text = "Congratulations!"
                        title.setTextColor(Color.parseColor("#0F9D58"))
                    }
                    else {
                        title.text = "Wordle failed! Better Luck Next time"
                        title.setTextColor(Color.parseColor("#DC143C"))
                    }
                }
                guess3 -> {
                    Toast.makeText(it.context, "Max number of guesses used", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    // would throw an error but I don't know how to do that
                }
            }

        }

        resetButton.setOnClickListener {
            Guess1.text = "XXXX"
            Guess1Check.text = "XXXX"
            Guess2.text = "XXXX"
            Guess2Check.text = "XXXX"
            Guess3.text = "XXXX"
            Guess3Check.text = "XXXX"
            title.text = "Wordle"
            title.setTextColor(Color.parseColor("#000000"))
            wordToGuess = getRandomFourLetterWord()
            answer.visibility = View.GONE
            answer.text = wordToGuess
            println(wordToGuess)
            curState = guess0
        }

    }




    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String, wordToGuess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
}