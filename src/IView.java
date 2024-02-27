import java.util.Scanner;

public interface IView {
    public void updateBoard(char[][] board);

    public int takeInput();
    public void alert(String message);
    public void requestInput(String message);
}
