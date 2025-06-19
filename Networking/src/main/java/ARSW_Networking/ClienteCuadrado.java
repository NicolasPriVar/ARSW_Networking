package ARSW_Networking;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteCuadrado {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 35000;

        try (
                Socket socket = new Socket(host, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner scanner = new Scanner(System.in);
        ) {
            System.out.print("Ingrese un número para calcular su cuadrado: ");
            String numero = scanner.nextLine();

            out.println(numero);

            String respuesta = in.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);

        } catch (IOException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }
}
