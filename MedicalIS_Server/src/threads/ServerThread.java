/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import communication.Operations;
import communication.Response;
import communication.ResponseType;
import communication.Sender;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maja
 */
public class ServerThread extends Thread {

    private ServerSocket serverSocket;
    private List<ClientHandle> clients;

    public ServerThread() throws IOException {
        clients = new ArrayList<>();
        serverSocket = new ServerSocket(9000);
    }

    @Override
    public void run() {
        while (!serverSocket.isClosed()) {
            System.out.println("Waiting for clients");
            try {
                Socket socket = serverSocket.accept();
                System.out.println("A client connected");
                ClientHandle client = new ClientHandle(socket);
                client.start();
                clients.add(client);

            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
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
            ex.printStackTrace();
        }
    }

    public void stopServer() throws IOException {
        stopClients();
        serverSocket.close();
    }



}
