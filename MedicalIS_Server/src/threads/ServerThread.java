/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import communication.Operations;
import communication.Response;
import communication.ResponseType;
import communication.Sender;
import constants.MyServerConstants;
import controller.ServerController;
import domain.Laborant;
import domain.Lekar;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maja
 */
public class ServerThread extends Thread {

    private ServerSocket serverSocket;
    private List<ClientHandle> clients;
    int port;

    public ServerThread() throws IOException {
        clients = new ArrayList<>();

        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(MyServerConstants.SERVER_CONFIG_FILE);
            prop.load(input);
            port = Integer.valueOf(prop.getProperty(MyServerConstants.PORT));
        } catch (IOException ex) {
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
            serverSocket = new ServerSocket(port);
        }
    }

        @Override
        public void run
        
            () {
        while (!serverSocket.isClosed()) {
                System.out.println("Waiting for clients");
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("A client connected");
                    ClientHandle client = new ClientHandle(socket);
                    client.start();
                    clients.add(client);
                } catch (IOException ex) {
                    //Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void stopClients() {
        Response response = new Response();
        try {
            for (ClientHandle cl : clients) {
                //  System.out.println(cl.getKorisnik().getEmail());

                response.setOperation(Operations.SHUTDOWN);
                response.setResponseType(ResponseType.SUCCESS);
                new Sender(cl.getSocket()).send(response);
                cl.getSocket().close();
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
    }

    public void stopServer() throws IOException {
        stopClients();
        serverSocket.close();
    }

    public void sendRefreshToAll() {
        Response response = new Response();
        response.setOperation(Operations.REFRESH);
        response.setResponseType(ResponseType.SUCCESS);
        if (!clients.isEmpty()) {
            for (ClientHandle c : clients) {
                try {
                    //if ((c.getLekar()!=null && !c.getLekar()!=)) {
                    new Sender(c.getSocket()).send(response);
                } catch (Exception ex) {
                    Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public void logoutLekar(Lekar lekar) {
         for (ClientHandle cl : clients) {
            if (cl.getLekar()!=null && cl.getLekar().getUsername().equals(lekar.getUsername())) {
                try {
                    cl.getSocket().close();
                } catch (IOException ex) {
                    Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }
        
    }
    public void logoutLaborant(Laborant laborant) {
        for (ClientHandle cl : clients) {
            if (cl.getLaborant()!=null && cl.getLaborant().getUsername().equals(laborant.getUsername())) {
                try {
                    cl.getSocket().close();
                } catch (IOException ex) {
                    Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }
    }
}
