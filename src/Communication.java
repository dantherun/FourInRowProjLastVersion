import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Communication {
    final String SERVER_ADDRESS = "127.0.0.1"; // Replace with the server address
    final static int PORT = 8080;

    private Socket socket;
    PrintWriter writer;
    BufferedReader reader;

    public Communication(){
        try {
            socket = new Socket(SERVER_ADDRESS, PORT);
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void openConnection()
    {
        try {
            socket = new Socket(SERVER_ADDRESS, PORT);
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void sendMove(String moveDirection)
    {
        writer.println(moveDirection);
    }

    public String receiveMove()
    {
        String move = "";

        try
        {
            move = reader.readLine();
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return move;
    }

}