package ARSW_Networking;

import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HoraServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(9876);
        byte[] buf = new byte[1024];

        System.out.println("Servidor de hora iniciado");

        while (true) {
            DatagramPacket request = new DatagramPacket(buf, buf.length);
            socket.receive(request);

            String horaActual = new SimpleDateFormat("HH:mm:ss").format(new Date());
            byte[] responseBytes = horaActual.getBytes();

            DatagramPacket response = new DatagramPacket(
                    responseBytes,
                    responseBytes.length,
                    request.getAddress(),
                    request.getPort()
            );
            socket.send(response);
        }
    }
}
