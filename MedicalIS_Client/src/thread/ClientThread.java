/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import communication.Operations;
import communication.Receiver;
import communication.Response;
import controller.ClientController;
import domain.Laborant;
import domain.Lekar;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maja
 */
public class ClientThread extends Thread {

    private Socket socket;
    private Lekar lekar;
    private Laborant laborant;

    //FrmLogin frmLogin;
   // FrmMain frmMain;

    public ClientThread() {
        try {
            this.socket = new Socket("127.0.0.1", 9000);
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
            try {
                Response response = (Response) new Receiver(socket).receive();
                handle(response);
            } catch (Exception ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void handle(Response response) throws Exception {
        switch (response.getOperation()) {
            case Operations.LOGIN_LEKAR:
                ClientController.getInstance().loginLekarResponse(response);
                break;
            case Operations.GET_PACIJENT:
                ClientController.getInstance().showKartonPacijenta(response);
                break;
            case Operations.GET_REZULTAT:
                ClientController.getInstance().showRezultat(response);
                break;
            
        }
    }
    
}
