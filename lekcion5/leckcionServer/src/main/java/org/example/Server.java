package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    public Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }
    public void runServer(){// Метод запускает сам Socked
        try {
            while (!serverSocket.isClosed()){ // isClosed() - Проверяет состояние вызванного объекта SQLServerConnection.
                Socket socket = serverSocket.accept();
                System.out.println("Подключен новый клиент");
                Main client = new Main(socket);
                Thread thread = new Thread((Runnable) client);
                thread.start();
            }
        }catch (IOException e){
            closeSocket(); //Закрываем сокет
        }
    }
    public void closeSocket(){
        try {
            if(serverSocket != null)serverSocket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args)throws IOException {
        ServerSocket serverSocket = new ServerSocket(1300);
        Server server = new Server(serverSocket);
        server.runServer();

    }

}