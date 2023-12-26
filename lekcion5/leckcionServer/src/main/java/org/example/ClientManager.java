package org.example;

import com.mysql.cj.x.protobuf.Mysqlx;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManager implements Runnable{
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;

    public static ArrayList<ClientManager> client = new ArrayList<>();
    public ClientManager(Socket socket){
        try {
            this.socket = socket;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // метод OutputStream возвращает поток вывода клиентского сокета
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Класс DataInputStream читает из потока любые примитивные символы, типы данных, массивы, и строки
            name = bufferedReader.readLine();// Читаем имя клиента
            client.add(this);
            // Метод отправки сообщения всем участникам чата
          //  broadcastMessage("Server: " + name + " подключился к чату");
            bropcastMessage("Server: " + name + " подключился к чату");
        }catch (IOException e){
            // метод для полного закрытия
            closeEeverything(socket, bufferedReader, bufferedWriter);
        }
    }
   // public void broadcastMessage(){}
    public void closeEeverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        //Метод удаления клиента
        removeClient();
        try {
            if (bufferedReader != null){
                bufferedReader.close();
            }
            if (bufferedWriter != null){
                bufferedWriter.close();
            }
            if (socket != null){
                socket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
 //   printStackkTrace(){}
    public void removeClient(){
        client.remove(this);
        bropcastMessage("Server: " + name + " покинул чат");
    }
    @Override
    public void run(){
        String massageFromClient;
        while (socket.isConnected()){
            try {
                massageFromClient = bufferedReader.readLine();
                bropcastMessage(massageFromClient);
            }catch (IOException e){
                closeEeverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }
    public void bropcastMessage(String messageToSend){
        for (ClientManager clientManager: client) {
            try {
                if (!clientManager.name.equals(name)){
                    clientManager.bufferedWriter.write(messageToSend);
                    clientManager.bufferedWriter.newLine();
                    clientManager.bufferedWriter.flush();
                }
            }catch (IOException e){
                closeEeverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }
}
