public class Bitmap {
    private long player1;
    private long player2;
    public Bitmap(){
         player1 = 0;
         player2 = 0;
    }

    public void reset(){
        player1 = 0;
        player2 = 0;
    }

    // inserts piece. it there isn't place it will return false
    public int insertPiece(int col, int row, int player){
        long mask = 1;

        if(row > 7)
            return -1;

        if(((player1 | player2) & (mask << 8 * row) << col) == 0){
            if(player == 0)
                player1 = player1 | (mask << 8 * row) << col;
            else
                player2 = player2 | (mask << 8 * row) << col;
            return row;
        }

        return insertPiece(col, row + 1, player);
    }

    public int checkFourPieces(int player, int x, int y, int direction){
        int diagonalPiecesNumber = 0;
        long playerToCheck = player == 0 ? player1 : player2;
        long mask = 1;

        if(x < 0 || x > 7 || y < 0 || y > 7 || (playerToCheck & (mask << (8 * y)) << x) == 0)
            return 0;

        if (direction == 0)
            diagonalPiecesNumber += checkFourPieces(player, x - 1, y - 1, direction);

        else if (direction == 1)
            diagonalPiecesNumber += checkFourPieces(player, x + 1, y - 1, direction);

        else if (direction == 2)
            diagonalPiecesNumber += checkFourPieces(player, x, y - 1, direction);

        else if (direction == 3)
            diagonalPiecesNumber += checkFourPieces(player, x - 1, y, direction);

        else
            diagonalPiecesNumber += checkFourPieces(player, x + 1, y, direction);

        diagonalPiecesNumber++;

        return diagonalPiecesNumber;
    }
    public char[][] convertToMatrix(){
        long mask = 1;
        char[][] matrix = new char[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if((player1 & ((mask << 8 * i) << j)) != 0)
                    matrix[7 - i][7 - j] = '*';
                else if ((player2 & ((mask << 8 * i) << j)) != 0)
                    matrix[7 - i][7 - j] = '+';
            }
        }

        return matrix;
    }

    public boolean isFull(){
        return (player1 | player2) == -1;
    }
}
