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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Client;

/**
 *
 * @author Maja
 */
public class ClientThread extends Thread {

    private Socket socket;
    private Lekar lekar;
    private Laborant laborant;

    private int port;

    public ClientThread() {
        try {
            setPort();
            this.socket = new Socket("127.0.0.1", port);
        } catch (IOException ex) {
            System.out.println("Greska u konektovanju!");
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
            case Operations.GET_REZULTATI:
                ClientController.getInstance().showRezultati(response);
                break;
            case Operations.INSERT_PACIJENT:
                ClientController.getInstance().notifyInsertPacijent(response);
                break;
            case Operations.SHUTDOWN:
                ClientController.getInstance().shutdown();
                break;
            case Operations.INSERT_UPUT:
                ClientController.getInstance().notifyInsertUput(response);
                break;
            case Operations.LOGIN_LAB:
                ClientController.getInstance().loginLaborantResponse(response);
                break;
            case Operations.GET_ALL_UPUT:
                ClientController.getInstance().setUputi(response);
                break;
            case Operations.GET_ALL_REZULTAT:
                ClientController.getInstance().setRezultati(response);
                break;
            case Operations.INSERT_REZULTAT:
                ClientController.getInstance().notifyInsertRezultat(response);
                break;
            case Operations.UPDATE_PACIJENT:
                ClientController.getInstance().notifyUpdatePacijent(response);
                break;
            case Operations.REFRESH:
                ClientController.getInstance().refresh();
                break;
            case Operations.GET_ALL_ANALIZA:
                ClientController.getInstance().showAnalize(response);
            case Operations.LOGOUT_LEKAR:
            case Operations.LOGOUT_LAB:
           //     socket.close();

        }
    }

    private void setPort() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("../MedicalIS_Server/config/server.properties");
            prop.load(input);
            port = Integer.valueOf(prop.getProperty("port"));

        } catch (IOException ex) {
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
