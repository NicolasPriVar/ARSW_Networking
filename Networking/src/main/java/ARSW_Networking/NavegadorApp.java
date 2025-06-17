package ARSW_Networking;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class NavegadorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese una URL (ejemplo: https://www.google.com): ");
        String urlInput = scanner.nextLine();

        try {
            URL url = new URL(urlInput);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );

            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("resultado.html")
            );

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }

            reader.close();
            writer.close();

            System.out.println("Contenido guardado en resultado.html");

        } catch (MalformedURLException e) {
            System.out.println("URL inválida: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al leer o guardar datos: " + e.getMessage());
        }
    }
}
