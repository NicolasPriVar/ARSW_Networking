package ARSW_Networking;

import java.net.*;
import java.io.*;
import java.util.*;
public class ServidorCuadrado {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
            System.out.println("Escuchando respuesta...");
        } catch (IOException e) {
            System.err.println("No se puede escuhar por el puerto 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        clientSocket.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            try {
                int numero = Integer.parseInt(inputLine);
                int square = numero * numero;
                System.out.println("El cuadrado es: " + square);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida: " + inputLine);
            }
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}