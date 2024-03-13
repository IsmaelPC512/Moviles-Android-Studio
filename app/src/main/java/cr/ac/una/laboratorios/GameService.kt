package cr.ac.una.laboratorios

class GameService {

    enum class GameState {
        player1Win, player2Win, tie, unfinished
    }

    private lateinit var table: Array<Array<Char>>

    // Function that create a table with blank spaces
    fun newTable(): Array<Array<Char>> {
        table = Array(3) {Array(3){' '} }
        return table
    }
    // Function that handle all possible win states of both players
    fun gameStatus(player1: Boolean): GameState {
        if (player1) {
            // All possible win states of player "O"
            val row1 = table[0][0] == 'O' && table[0][1] == 'O' && table[0][2] == 'O'
            val row2 = table[1][0] == 'O' && table[1][1] == 'O' && table[1][2] == 'O'
            val row3 = table[2][0] == 'O' && table[2][1] == 'O' && table[2][2] == 'O'
            val column1 = table[0][0] == 'O' && table[1][0] == 'O' && table[2][0] == 'O'
            val column2 = table[0][1] == 'O' && table[1][1] == 'O' && table[2][1] == 'O'
            val column3 = table[0][2] == 'O' && table[1][2] == 'O' && table[2][2] == 'O'
            val diagonal1 = table[0][0] == 'O' && table[1][1] == 'O' && table[2][2] == 'O'
            val diagonal2 = table[0][2] == 'O' && table[1][1] == 'O' && table[2][0] == 'O'
            // "if" that checks if player 1 win, or is a "tie" in this turn or the game continue
            return if (row1 || row2 || row3 || column1 || column2 || column3 || diagonal1 || diagonal2) {
                GameState.player1Win
            } else if (checkGameBoard()){
                GameState.tie
            } else {
                GameState.unfinished
            }
        } else {
            // All possible win states of player "X"
            val row1 = table[0][0] == 'X' && table[0][1] == 'X' && table[0][2] == 'X'
            val row2 = table[1][0] == 'X' && table[1][1] == 'X' && table[1][2] == 'X'
            val row3 = table[2][0] == 'X' && table[2][1] == 'X' && table[2][2] == 'X'
            val column1 = table[0][0] == 'X' && table[1][0] == 'X' && table[2][0] == 'X'
            val column2 = table[0][1] == 'X' && table[1][1] == 'X' && table[2][1] == 'X'
            val column3 = table[0][2] == 'X' && table[1][2] == 'X' && table[2][2] == 'X'
            val diagonal1 = table[0][0] == 'X' && table[1][1] == 'X' && table[2][2] == 'X'
            val diagonal2 = table[0][2] == 'X' && table[1][1] == 'X' && table[2][0] == 'X'
            // "if" that checks if player 2 win, or is a "tie" in this turn or the game continue
            return if (row1 || row2 || row3 || column1 || column2 || column3 || diagonal1 || diagonal2) {
                GameState.player2Win
            } else if (checkGameBoard()){
                GameState.tie
            } else {
                GameState.unfinished
            }
        }
    }
    // Function that return true if the board game is full of "X" and "O", if is not return false
    private fun checkGameBoard(): Boolean {
        for (i in table.indices) {
            for (j in table[i].indices) {
                if (table[i][j] == ' ') {
                    return false
                }
            }
        }
        return true
    }
}