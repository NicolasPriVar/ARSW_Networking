package ARSW_Networking;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class ChatApp {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Tu nombre: ");
            String nombre = scanner.nextLine();

            System.out.println("Puerto para publicar tu objetivo (ej. 1099): ");
            int miPuerto = Integer.parseInt(scanner.nextLine());

            ChatImpl chat = new ChatImpl(nombre);
            LocateRegistry.createRegistry(miPuerto);
            Naming.rebind("rmi://localhost:"+ miPuerto + "/" + nombre, chat);
            System.out.println("Objeto remoto registrado como: " + nombre);

            System.out.println("IP del otro usuario: ");
            String ip = scanner.nextLine();

            System.out.print("Puerto del otro usuario: ");
            String puertoOtro = scanner.nextLine();

            System.out.println("Nombre  del otro usuario: ");
            String nombreOtro = scanner.nextLine();

            ChatInterface amigo = (ChatInterface) Naming.lookup("rmi://" + ip + ":" + puertoOtro + "/" + nombreOtro);
            System.out.println("Conectado con "+nombreOtro + ". ¡A chatear!");

            while (true) {
                System.out.print("> ");
                String mensaje = scanner.nextLine();
                amigo.recibirMensaje(nombre + ": " + mensaje);
            }
        } catch (Exception e) {
            System.out.println("Error en el chat: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
