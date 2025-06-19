package ARSW_Networking;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatInterface extends Remote {
    void recibirMensaje(String mensaje) throws RemoteException;
}
