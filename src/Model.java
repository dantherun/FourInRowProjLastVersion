public class Model {
    private Bitmap players;
    public Model(){
        players = new Bitmap();
    }

    public void newGame(){
        players.reset();
    }

    public int makeMove(int player, int x)
    {
        if(x < 0 || x > 7)
            return -1;
        return players.insertPiece(x, 0, player);

    }

    public boolean checkWin(int player, int x, int y){
        if(players.checkFourPieces(player, x, y, 0) == 4 ||
                players.checkFourPieces(player, x, y, 1) == 4 ||
                players.checkFourPieces(player, x, y, 2) == 4 ||
                players.checkFourPieces(player, x, y, 3) == 4 ||
                players.checkFourPieces(player, x, y, 4) == 4)
            return true;

        return false;
    }

    public boolean checkTie(){
        return players.isFull();
    }

    public char[][] getBoard(){
        return players.convertToMatrix();
    }

    public void updatePlayer(int x, int y, int player){
        players.insertPiece(x, y, player);
    }

}
