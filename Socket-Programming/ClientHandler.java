package com.company;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    Socket socket = new Socket();
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    String clientUserName;

    public ClientHandler(Socket socket)
    {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUserName = bufferedReader.readLine();
            clientHandlers.add(this);
            broadcastMessage("SERVER: " + clientUserName + "has entered chat group.");
        }
        catch (Exception e)
        {
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }




    @Override
    public void run() {
    String messageFromClient;

    while (socket.isConnected())
    {
        try
        {
            messageFromClient = bufferedReader.readLine();
            broadcastMessage(messageFromClient);
        }
        catch (Exception e)
        {
            closeEverything(socket,bufferedReader,bufferedWriter);
            break;
        }
    }
    }

    public void broadcastMessage(String messageToSent)
    {
        for(ClientHandler clientHandler : clientHandlers)
        {
            try {
                if(!clientHandler.clientUserName.equals(clientUserName))
                {
                    clientHandler.bufferedWriter.write(messageToSent);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            }
            catch (Exception e)
            {
                closeEverything(socket,bufferedReader,bufferedWriter);
            }

        }
    }

    public void removeClient()
    {
        clientHandlers.remove(this);
        broadcastMessage("SERVER: " + clientUserName + " has left the chat!.");
    }

    public void closeEverything(Socket socket,BufferedReader bufferedReader, BufferedWriter bufferedWriter)
    {
        removeClient();
        try {
            if(bufferedReader != null)
                bufferedReader.close();
            if(bufferedWriter !=null)
                bufferedWriter.close();
            if(socket != null)
                socket.close();
        }
        catch (IOException e)
        {
            e.getStackTrace();
        }
    }
}
