package sockets;

import java.io.*;
import java.net.*;

public class ServerSide {
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream out;

    public ServerSide(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            System.out.println("Connectado!!");

            input = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            // hihi
            String message = "Ola, Servidor!";
            out.writeUTF(message);

            String response = input.readUTF();
            System.out.println("Resposta do Servidor: " + response);

        } catch (UnknownHostException e) {
            System.out.println("Cliente nao encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("erro de IO: " + e.getMessage());
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
                if (out != null) {
                    out.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                System.out.println("Erro Fechando Recursos: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        String ip = "127.0.0.1"; // ok
        int port = 12345; // ok
        new ServerSide(ip, port);
    }
}