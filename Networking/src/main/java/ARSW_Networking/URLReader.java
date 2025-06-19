package ARSW_Networking;

import java.net.*;
import java.util.*;
public class URLReader {
    public static void main( String[] args ) throws Exception {
        URL google = new URL("http://www.google.com/");

        List<String> metodos = new ArrayList<>();

        metodos.add("Protocolo: " + google.getProtocol());
        System.out.println ("Protocolo: " + google.getProtocol());
        metodos.add("Autoridad: " + google.getAuthority());
        System.out.println("Autoridad: " + google.getAuthority());
        metodos.add("Host: " + google.getHost());
        System.out.println("Host: " + google.getHost());
        metodos.add("Puerto: " + google.getPort());
        System.out.println("Puerto: " + google.getPort());
        metodos.add("Path: " + google.getPath());
        System.out.println("Path: " + google.getPath());
        metodos.add("Consulta: " + google.getQuery());
        System.out.println("Consulta: " + google.getQuery());
        metodos.add("Archivo: " + google.getFile());
        System.out.println("Archivo: " + google.getFile());
        metodos.add("Ref: " + google.getRef());
        System.out.println("Ref: " + google.getRef());
    }
}
