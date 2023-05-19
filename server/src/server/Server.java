package server;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.*;
public class Server {

    public static void main(String [] args)
    {
    	
    	
        Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
	       try (ServerSocket serverSocket = new ServerSocket(1000)) {
					System.out.println("Server running");
					while(true)
					{
						 System.out.println("here in while true");
						 Socket socket = serverSocket.accept();
						 System.out.println("Server accept the connection");
						 DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
						 DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
						 Scanner scanner=new Scanner(System.in);
						 System.out.println(dataInputStream.readUTF());
						 String response=scanner.nextLine();
						 dataOutputStream.writeUTF(response);
						 scanner.close();
						 dataInputStream.close();
						 socket.close();
	
					}
					
					
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    });
        thread.start();
    }
}
