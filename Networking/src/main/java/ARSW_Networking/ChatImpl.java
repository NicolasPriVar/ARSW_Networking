package ARSW_Networking;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatImpl extends UnicastRemoteObject implements ChatInterface {
    private String nombre;

    protected ChatImpl(String nombre) throws RemoteException {
        super();
        this.nombre = nombre;
    }

    @Override
    public void recibirMensaje(String mensaje) throws RemoteException {
        System.out.println("\n[Mensaje recibido] " + mensaje);
        System.out.print("> ");
    }
}
