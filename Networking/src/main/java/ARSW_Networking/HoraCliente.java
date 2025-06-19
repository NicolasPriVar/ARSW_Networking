package ARSW_Networking;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

public class HoraCliente {
    private static final int PUERTO = 9876;
    private static final String SERVIDOR = "localhost";
    private static final int TIMEOUT = 2000;

    public static void main(String[] args) {
        String horaActual = "No se ha recibido hora aún";

        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(TIMEOUT);

            while (true) {
                try {
                    byte[] mensaje = "HORA".getBytes();
                    DatagramPacket peticion = new DatagramPacket(
                            mensaje,
                            mensaje.length,
                            InetAddress.getByName(SERVIDOR),
                            PUERTO
                    );
                    socket.send(peticion);

                    byte[] buffer = new byte[1024];
                    DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);
                    socket.receive(respuesta);

                    horaActual = new String(respuesta.getData(), 0, respuesta.getLength());
                } catch (SocketTimeoutException e) {
                    System.out.println("No se recibió respuesta, usando hora previa.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }

                System.out.println("Hora actual: " + horaActual);
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
