/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import communication.Operations;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.ResponseType;
import communication.Sender;
import controller.ServerController;
import domain.KartonPacijenta;
import domain.Laborant;
import domain.Lekar;
import domain.Rezultat;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maja
 */
public class ClientHandle extends Thread {

    private Socket socket;
    private Lekar lekar;
    private Laborant laborant;

    public ClientHandle(Socket socket) {
        this.socket = socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
            try {
                Request request = (Request) new Receiver(socket).receive();
                Response response = handle(request);
                new Sender(socket).send(response);
            } catch (Exception ex) {
                Logger.getLogger(ClientHandle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private Response handle(Request request) {
        Response response = null;
        switch (request.getOperation()) {
            case Operations.LOGIN_LEKAR:
                response = loginLekar(request);
                break;
            case Operations.GET_PACIJENT:
                response = sendKartonPacijenta(request);
                break;
            case Operations.GET_REZULTAT:
                response = sendRezultat(request);
                break;
            case Operations.INSERT_PACIJENT:
                response = ServerController.getInstance().insertKartonPacijetna(request);
                break;
            case Operations.INSERT_UPUT:
                response = ServerController.getInstance().insertUput(request);
                break;
            case Operations.GET_ALL_UPUT:
                response = ServerController.getInstance().sendAllUputi();
                break;
            case Operations.GET_ALL_REZULTAT:
                response = ServerController.getInstance().sendAllRezultati();
                break;
            case Operations.LOGIN_LAB:
                response = loginLab(request);
            default:
        }
        return response;
    }

    private Response loginLekar(Request request) {
        Response response = new Response();
        response.setOperation(Operations.LOGIN_LEKAR);
        Lekar reqL = (Lekar) request.getArgument();
        Lekar l = ServerController.getInstance().loginLekar(reqL);
        if (l != null) {
            response.setResponse(l);
            response.setResponseType(ResponseType.SUCCESS);
            lekar = l;
            System.out.println(lekar.getIme());
        } else {
            response.setResponseType(ResponseType.ERROR);
        }

        return response;
    }

    private Response sendKartonPacijenta(Request request) {
        Response response = new Response();
        KartonPacijenta p = ServerController.getInstance().sendKartonPacijenta(request);
        System.out.println(p);
        System.out.println(p.getJmbg().toString() + ", " + p.getIme());
        if (!p.equals(null)) {
            response.setResponse(p);
            System.out.println(response.getResponse());
            response.setResponseType(ResponseType.SUCCESS);
            response.setOperation(Operations.GET_PACIJENT);
        } else {
            response.setResponseType(ResponseType.ERROR);
            response.setException(new Exception("Nepostojeci karton pacijenta!"));
        }
        return response;
    }

    private Response sendRezultat(Request request) {
        Response response = new Response();
        Rezultat r = ServerController.getInstance().getRezultat(request);
        if (r != null) {
            response.setResponse(r);
            response.setOperation(Operations.GET_REZULTAT);
            response.setResponseType(ResponseType.SUCCESS);
        } else {
            response.setResponseType(ResponseType.ERROR);
            response.setException(new Exception("Rezultat nije pronadjen!"));
        }
        return response;
    }

    private Response loginLab(Request request) {
        Response response = new Response();
        response.setOperation(Operations.LOGIN_LAB);
        Laborant lab = (Laborant) request.getArgument();
        Laborant l = ServerController.getInstance().loginLaborant(lab);
        if (l != null) {
            response.setResponse(l);
            response.setResponseType(ResponseType.SUCCESS);
            laborant = l;
        } else {
            response.setResponseType(ResponseType.ERROR);
        }

        return response;
    }

}
