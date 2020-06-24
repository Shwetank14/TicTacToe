package com.example.thetictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener{
    var PLAYER = true
    var TURNS = 0
    var boardStatus = Array(3){IntArray(3)}
    lateinit var board : Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(
            arrayOf(button1,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)
        )
        for(i in board){
            for(button in i){
                button.setOnClickListener(this)
            }
        }
        initializeboard()           //initializing the board
        reset.setOnClickListener {
            rst.text = ""
            PLAYER = true
            TURNS = 0
            initializeboard()
        }

    }

    private fun initializeboard() {             //Initializing the board
        TURNS = 0
        for(i in 0..2){
            for(j in 0..2){
                boardStatus[i][j] = -1          //Setting board status as -1 initially
                board[i][j].isEnabled = true    //Enabling all the buttons of the board
                board[i][j].text = ""
            }
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.button1 -> {
                updateStatus(row = 0, col = 0 , player = PLAYER)
            }
            R.id.button2 -> {
                updateStatus(row = 0, col = 1 , player = PLAYER)
            }
            R.id.button3 -> {
                updateStatus(row = 0, col = 2 , player = PLAYER)
            }
            R.id.button4 -> {
                updateStatus(row = 1, col = 0 , player = PLAYER)
            }
            R.id.button5 -> {
                updateStatus(row = 1, col = 1 , player = PLAYER)
            }
            R.id.button6 -> {
                updateStatus(row = 1, col = 2 , player = PLAYER)
            }
            R.id.button7 -> {
                updateStatus(row = 2, col = 0 , player = PLAYER)
            }
            R.id.button8 -> {
                updateStatus(row = 2, col = 1 , player = PLAYER)
        }
            R.id.button9 -> {
                updateStatus(row = 2, col = 2 , player = PLAYER)
            }
        }
        TURNS++
        PLAYER = !PLAYER
        if(PLAYER == true){
            updateDisplay("Player X turn")
        }
        else{
            updateDisplay("PLAYER 0 turn")
        }

        if(TURNS == 9){
            updateDisplay("Game drawn!!")
        }
        checkWinner()
    }

    private fun checkWinner() {

        // diagonals
        if((boardStatus[0][0] == boardStatus[1][1]  && boardStatus[0][0] == boardStatus[2][2])){

            if(boardStatus[0][0] == 1){
                updateDisplay("Player X Wins")
            }else if(boardStatus[0][0] == 0){
                updateDisplay("Player 0 Wins")
            }
        }
        else if(boardStatus[0][2] == boardStatus[1][1]  && boardStatus[0][2] == boardStatus[2][0]){
            if(boardStatus[0][2] == 1){
                updateDisplay("Player X Wins")
            }else if(boardStatus[0][2] == 0){
                updateDisplay("Player 0 Wins")
            }
        }


        //For Horizontal rows

        for(i in 0..2){
            if(boardStatus[i][0] == boardStatus[i][1]  && boardStatus[i][0] == boardStatus[i][2]){
                if(boardStatus[i][0] == 1){
                    updateDisplay("Player X Wins")
                    break
                }else if(boardStatus[i][0] == 0){
                    updateDisplay("Player 0 Wins")
                    break
                }
            }
        }
        // Columns
        for(i in 0..2){
            if(boardStatus[0][i] == boardStatus[1][i]  && boardStatus[0][i] == boardStatus[2][i]){
                if(boardStatus[0][i] == 1){
                    updateDisplay("Player X Wins")
                    break
                }else if(boardStatus[0][i] == 0){
                    updateDisplay("Player 0 Wins")
                    break
                }
            }
        }


    }

    private fun updateDisplay(s: String) {
        text2.text = s
        if(s.contains("Wins") || s.contains("drawn!!")) {
            rst.text = "Reset The board!!"
            if(s.contains("Wins")){
                disableButton()
            }
        }
    }

    private fun disableButton() {
        for(i in 0..2){
            for(j in 0..2){
                board[i][j].isEnabled = false
            }
        }
    }

    private fun updateStatus(row: Int, col: Int, player: Boolean) {
            val text = if(player)"X" else "0"
            val value = if(player) 1 else 0

        board[row][col].apply {
            isEnabled = false
            setText(text)
        }
        boardStatus[row][col] = value
    }
}