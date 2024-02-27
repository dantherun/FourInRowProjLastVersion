import java.util.Scanner;

public class View implements IView {
    private Presenter presenter;
    public View(){
        this.presenter = new Presenter(this);
    }

    public void updateBoard(char[][] board){
        System.out.println("----------------------------------------------------------------------------");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print("[" + board[i][j] + "]");
            }
            System.out.println();
        }
        System.out.println("----------------------------------------------------------------------------");
    }

    public int takeInput(){
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        return x;
    }

    public void alert(String message){
        System.out.println(message);
    }

    public void requestInput(String message) {
        System.out.print(message + " >> ");
    }
}
