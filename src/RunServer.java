import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class RunServer {

    public static void main(String[] args) {
        final int PORT = 8080;

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started. Waiting for client...");

            // open socket with first player
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client1 connected: " + clientSocket.getInetAddress());
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            Socket clientSocket2 = serverSocket.accept();
            System.out.println("Client2 connected: " + clientSocket2.getInetAddress());
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(clientSocket2.getInputStream()));
            PrintWriter writer2 = new PrintWriter(clientSocket2.getOutputStream(), true);

            String inputLine = "";
            writer.println("You first");
            writer2.println("You second");
            boolean playing = true;
            while(playing)
            {

                inputLine = reader.readLine() ;
                System.out.println("received from player 1: " + inputLine);
                writer2.println(inputLine);
                System.out.println("sent from player 2: " + inputLine);

                if(inputLine.equals("finish") || inputLine.equals("tie"))
                    playing = false;

                else {
                    inputLine = reader2.readLine();
                    System.out.println("received from player 2: " + inputLine);
                    writer.println(inputLine);
                    System.out.println("sent from player 1: " + inputLine);

                    if (inputLine.equals("finish") || inputLine.equals("tie"))
                        playing = false;
                }
            }

            clientSocket.close();
            System.out.println("Server closed. 'Till the next time!");


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}