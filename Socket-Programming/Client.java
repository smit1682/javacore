package com.company;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Socket socket = new Socket();
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    String clientUsername;

    public Client(Socket socket, String clientUsername){
        try {
            this.socket = socket;
            this.bufferedReader =new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter =new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.clientUsername = clientUsername;
        }
        catch (IOException e)
        {
            closeEverything(socket,bufferedReader,bufferedWriter);
        }

    }

    public void sendMessage()
    {
        try {
            bufferedWriter.write(clientUsername);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected())
            {
                String messageToSent = scanner.nextLine();
                bufferedWriter.write(clientUsername +": " + messageToSent);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }

        }
        catch (Exception e)
        {
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }

    public void listenForMessage()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String messageFromGroupChat;

                while (socket.isConnected())
                {
                    try {
                        messageFromGroupChat = bufferedReader.readLine();
                        System.out.println(messageFromGroupChat);
                    }
                    catch (Exception e)
                    {
                        closeEverything(socket,bufferedReader,bufferedWriter);
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket,BufferedReader bufferedReader, BufferedWriter bufferedWriter)
    {
        try {
            if(bufferedReader != null)
                bufferedReader.close();
            if(bufferedWriter !=null)
                bufferedWriter.close();
            if(socket != null)
                socket.close();
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();

        Socket socket = new Socket("localhost",1234);
        Client client = new Client(socket,username);
        client.listenForMessage();
        client.sendMessage();
    }


}
