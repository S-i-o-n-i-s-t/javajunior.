package ru.geekbrains.chat.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManager implements Runnable {

    private final Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;

    public final static ArrayList<ClientManager> clients = new ArrayList<>();

    public ClientManager(Socket socket) {
        this.socket = socket;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            name = bufferedReader.readLine();
            clients.add(this);
            System.out.println(name + " подключился к чату.");
            broadcastMessage("Server: " + name + " подключился к чату.");
        }
        catch (IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }


    }

    @Override
    public void run() {
        String massageFromClient;

        while (socket.isConnected()) {
            try {
                massageFromClient = bufferedReader.readLine();
                /*if (massageFromClient == null){
                    // для  macOS
                    closeEverything(socket, bufferedReader, bufferedWriter);
                    break;
                }*/
                broadcastMessage(massageFromClient);
            }
            catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }
    // Подумайте, как организовать отправку ЛИЧНЫХ сообщений в контексте нашего чата,
    // доработайте поддержку отправки личных сообщений, небольшую подсказку я дал в конце семинара.

    private void nameSenderDelde(StringBuilder stringBuilder, String name){
        String nameDelde = name + ": @";
        int i = stringBuilder.indexOf(nameDelde);
        if (i != -1) {
            stringBuilder.delete(i, i + nameDelde.length());
        }
    }
    private String recipientName(StringBuilder stringBuilder, String name){
        return String.valueOf(stringBuilder.delete(name.length(), stringBuilder.length()));
    }
    private void createBroadcastMessage(ClientManager client, String message){
        try {
                client.bufferedWriter.write(message);
                client.bufferedWriter.newLine();
                client.bufferedWriter.flush();
        }
        catch (IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
    private void broadcastMessage(String message){
            StringBuilder stringBuilder = new StringBuilder(message);
            boolean test = true;
            char str = stringBuilder.charAt(name.length() + 2);
            nameSenderDelde(stringBuilder, name);
            for (ClientManager client: clients) {
                if (!client.name.equals(name)){
                    if (str == '@' && client.name.equals(recipientName(stringBuilder, client.name))){
                        createBroadcastMessage(client, message);
                        test = false;
                    }
                }
            }
            if (test == true) {
                for (ClientManager client: clients) {
                    if (!client.name.equals(name)){
                        createBroadcastMessage(client, message);
                        }
                    }
            }

    }
    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        // Удаление клиента из коллекции
        removeClient();
        try {
            // Завершаем работу буфера на чтение данных
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            // Завершаем работу буфера для записи данных
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            // Закрытие соединения с клиентским сокетом
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeClient(){
        clients.remove(this);
        System.out.println(name + " покинул чат.");
        broadcastMessage("Server: " + name + " покинул чат.");
    }

}
   // mvn archetype:generate -DgroupId=com.example -DartifactId="myapp" -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
//https://www.jetbrains.com/ru-ru/idea/download/?section=windows
//
//        22:56
//        Екатерина Богатырева
//        https://rutracker.net/forum/index.php
//
//        22:56
//        Сергей Боровиков
//        https://www.jetbrains.com/toolbox-app/