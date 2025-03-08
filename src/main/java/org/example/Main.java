package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(4221);

            serverSocket.setReuseAddress(true);

            Socket clientSocket = serverSocket.accept();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );
            OutputStream clientOutput = clientSocket.getOutputStream();

            String clientInputLine = in.readLine();

            System.out.println(clientInputLine);

            String[] get = clientInputLine.split(" ");

            if (get[1].equals("/")) {
                clientOutput.write("HTTP/1.1 200 OK\r\n".getBytes());
                clientOutput.write("\r\n".getBytes());
            } else {
                clientOutput.write("HTTP/1.1 404 Not Found\r\n\r\n".getBytes());
            }


        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}