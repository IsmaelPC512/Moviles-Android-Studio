package cr.ac.una.laboratorios

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get

class MainActivity : AppCompatActivity() {

    private val gameService = GameService()
    private lateinit var table: Array<Array<Char>>
    private var gameOver = false
    private var player1 = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startGame()
        setUpListeners()

    }
    // Function that creates a new table with blank spaces
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun startGame(){
        val boardGame = findViewById<LinearLayout>(R.id.boardGame)
        table = gameService.newTable()
        gameOver = false
        player1 = true

        for (i in 0 until boardGame.childCount) {
            val row = boardGame[i] as LinearLayout
            for (j in 0 until row.childCount) {
                val position = row[j] as ImageView
                position.setImageDrawable(getDrawable(R.drawable.limpio))
            }
        }
    }
    // Function that set as ' ' every segment of the table
    private fun setUpListeners(){
        val boardGame = findViewById<LinearLayout>(R.id.boardGame)
        val reloadGame = findViewById<Button>(R.id.reloadGame)

        reloadGame.setOnClickListener {
            startGame()
        }

        for(i in 0 until boardGame.childCount) {
            val row = boardGame[i] as LinearLayout
            for(j in 0 until row.childCount){
                val position = row[j] as ImageView
                position.setOnClickListener{
                    if(!gameOver && table[i][j] == ' '){
                        setPosition(position, i,j)
                        val valGameStatus = gameService.gameStatus(player1)
                        if (player1 && valGameStatus == GameService.GameState.player1Win) {
                            showGameDialog("Jugador 1 Gano la Partida")
                            gameOver = true
                        } else if (!player1 && valGameStatus == GameService.GameState.player2Win) {
                            showGameDialog("Jugador 2 Gano la Partida")
                            gameOver = true
                        } else if (valGameStatus == GameService.GameState.tie) {
                            showGameDialog("Empate, juagar de nuevo?")
                            gameOver = true
                        } else {
                            player1 = !player1
                        }
                    }
                }
            }
        }
    }
    //
    private fun showGameDialog(message: String) {
        AlertDialog.Builder(this)
            .setTitle(message)
            .setPositiveButton("Jugar de Nuevo", {dialog, wich -> startGame()})
            .setNegativeButton("Cancelar", {dialog, wich -> dialog.dismiss()})
            .show()
    }
    //
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setPosition(view: ImageView, x: Int, y: Int){
        if(player1) {
            table[x][y] = 'O'
            view.setImageDrawable(getDrawable(R.drawable.circulo))
        } else {
            table[x][y] = 'X'
            view.setImageDrawable(getDrawable(R.drawable.cruz))
        }
    }

}