import java.net.ConnectException;
import java.util.Objects;

public class Presenter {
    private final IView view;
    private final Model model;
    private int player;
    private boolean gameOn;
    private Communication communication;
    private String moveDirection;
    private int row;
    private int col;


    public Presenter(IView view){
        this.view = view;
        model = new Model();
        communication = new Communication();
        //player = 0;
        String moveDirection = "";
        game();
    }

    public void receiveEnemyMove(String moveDirection){
        col = Integer.parseInt(moveDirection.split(",", 2)[0]);
        row = Integer.parseInt(moveDirection.split(",", 2)[1]);
        model.updatePlayer(col, row, 1 - player);
    }
    public void game(){
        model.newGame();
     //   player = 0;
        gameOn = true;

        if(!Objects.equals((moveDirection = communication.receiveMove()), "You first")){
            player = 0;
            view.alert("You are the second player. Your piece is: '*'. Wait until your opponent moves.");
            moveDirection = communication.receiveMove();
            receiveEnemyMove(moveDirection);
            view.updateBoard(model.getBoard());
            //player++;

        }

        else{
            player = 1;
            view.alert("You are the first player. Your piece is: '+'. You are the first to make the move.");
        }

        while (gameOn){
            /*
            view.alert("Player " + player % 2+ " please make move");
            move = view.takeInput();
            row = model.makeMove(player % 2, move);
            if(row == -1)
                view.alert("Illegal move. Move again");
            else{
                if(model.checkWin(player % 2, move, row)){
                    gameOn = false;
                    won();
                }

                model.print();
                view.updateBoard(model.getBoard());
                player++;
            }

             */

            view.requestInput("Please make move");
            col = view.takeInput();
            row = model.makeMove(player, col);
            if(row == -1)
                view.alert("Illegal move. Move again");

            else{
                communication.sendMove(String.valueOf(col) + "," + String.valueOf(row));
                view.updateBoard(model.getBoard());
                if(model.checkWin(player, col, row)){
                    gameOn = false;
                    playerWon();
                }

                else if(model.checkTie()){

                    gameOn = false;
                    playerTie();
                }

                else {
                    moveDirection = communication.receiveMove();
                    if(moveDirection.equals("finish")){
                        gameOn = false;
                        enemyWon();
                    }

                    else if(moveDirection.equals("tie")){
                        gameOn = false;
                        enemyTie();
                    }

                    else {
                        receiveEnemyMove(moveDirection);
                        view.updateBoard(model.getBoard());
                        if(model.checkWin(1 - player, col, row)){
                            gameOn = false;
                            enemyWon();
                        }

                        else if(model.checkTie()){
                            gameOn = false;
                            enemyTie();
                        }
                    }
                }


                // player++;
            }

           // player++;
        }
    }

    /*public void playerWon(){
        view.alert("Congratulations! Do you want to play again? (1, 0)");
        int playAgain = view.takeInput();
        if(playAgain == 1){
            gameOn = true;
            communication.sendMove("again");
            game();
        }

        else
            communication.sendMove("finish");
    }

    public void enemyWon(){
        view.alert("Enemy won. Wait to see if he wants to play again.");
        moveDirection = communication.receiveMove();
        if(moveDirection.equals("again")){
            gameOn = true;
            game();
        }
    }*/

    public void playerWon(){
        view.alert("Congratulations! You've won!");
    }

    public void enemyWon(){
        view.alert("Enemy has won. Best luck next time!");
        communication.sendMove("finish");
    }

    public void playerTie(){
        view.alert("There is a tie!");
    }

    public void enemyTie(){
        view.alert("There is a tie!");
        communication.sendMove("tie");
    }

}
