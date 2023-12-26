package org.example;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientExample2 {
    public static void main(String[] args) {
        try{
            // Реализация сервера
            InetAddress address = InetAddress.getLocalHost();
            Socket client = new Socket(address,1300);

            System.out.println(client.getInetAddress());
            System.out.println(client.getLocalPort());
            // Потоки
            InputStream inputStream = client.getInputStream(); // метод InputStrea возвращает поток ввода клиентского сокета
            OutputStream outputStream = client.getOutputStream(); // метод OutputStream возвращает поток вывода клиентского сокета
            DataInputStream dataInputStream = new DataInputStream(inputStream); // Класс DataInputStream читает из потока любые примитивные символы, типы данных, массивы, и строки
            PrintStream printStream = new PrintStream(outputStream);// Класс PrintStream добавляет функционал потоку (позволяет печатать строки)

            printStream.println("Привет");
            System.out.println(dataInputStream.readLine()); //Получаем строку и выводим в консоль
            client.close();

        }catch (UnknownHostException e){
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
