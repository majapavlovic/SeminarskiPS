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
import domain.Uput;
import java.net.Socket;
import java.util.ArrayList;
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
                //Logger.getLogger(ClientHandle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private Response handle(Request request) {
        Response response = null;
        switch (request.getOperation()) {
            case Operations.LOGIN_LEKAR:
                response = loginLekar(request);
                break;
            case Operations.GET_PACIJENT:       //OVDE SADA ISPROBAVAM ABSTRACT SO
                response = findKartonPacijenta(request);
                //response = ServerController.getInstance().findKartonPacijenta(request);
                break;
            case Operations.GET_REZULTATI:  // NOVO, VRACA REZULTATE ANALIZA JEDNOG UPUTA
                response = sendRezultati(request);
                break;
            case Operations.INSERT_PACIJENT:
                response = ServerController.getInstance().insertKartonPacijetna(request);
                break;
            case Operations.INSERT_UPUT: //NOVO
                response = ServerController.getInstance().insertUput(request);
                break;
            case Operations.GET_ALL_UPUT:                                       //NOVO U IZRADI
                response = sendAllUputi();
                break;
            case Operations.GET_ALL_REZULTAT:
                response = ServerController.getInstance().sendAllRezultati();
                break;
            case Operations.LOGIN_LAB:
                response = loginLab(request);
                break;
            case Operations.INSERT_REZULTAT:    //NOVO
                //response = ServerController.getInstance().insertRezultat(request);
                response = insertRezultat(request);
                break;
            case Operations.UPDATE_PACIJENT:
                response = ServerController.getInstance().updatePacijent(request);
                break;
            case Operations.REFRESH:
                ServerController.getInstance().sendRefreshToAll();
                break;
            case Operations.GET_ALL_ANALIZA:
                response = ServerController.getInstance().getAllAnalize();
                break;
            case Operations.LOGOUT_LEKAR:
                ServerController.getInstance().logoutLekar(request);
                break;
            case Operations.LOGOUT_LAB:
                ServerController.getInstance().logoutLab(request);
                break;
            default:
        }
        return response;
    }

    private Response loginLekar(Request request) {
        Response response = new Response();
        response.setOperation(Operations.LOGIN_LEKAR);
        Lekar reqL = (Lekar) request.getArgument();
        try {
            Lekar l = ServerController.getInstance().loginLekar(reqL);

            response.setResponse(l);
            response.setResponseType(ResponseType.SUCCESS);
            lekar = l;
            ServerController.getInstance().showLoggedUsers(l);
        } catch (Exception ex) {
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
        }

        return response;
    }

    private Response sendKartonPacijenta(Request request) {
        Response response = new Response();
        response.setOperation(Operations.GET_PACIJENT);
        KartonPacijenta p = ServerController.getInstance().sendKartonPacijenta(request, lekar);
        if (!p.equals(null)) {
            response.setResponse(p);
            response.setResponseType(ResponseType.SUCCESS);
        } else {
            response.setResponseType(ResponseType.ERROR);
            response.setException(new Exception("Nepostojeci karton pacijenta!"));
        }
        return response;
    }

    private Response sendRezultati(Request request) {
        Response response = new Response();
        response.setOperation(Operations.GET_REZULTATI);

        try {
            List<Rezultat> r = ServerController.getInstance().getListaRezultata(request);
            response.setResponse(r);
            response.setResponseType(ResponseType.SUCCESS);
        } catch (Exception ex) {

            response.setResponseType(ResponseType.ERROR);
            response.setException(new Exception("Rezultat nije pronadjen!"));
            ex.printStackTrace();
        }
        return response;
    }

    private Response loginLab(Request request) {
        Response response = new Response();
        response.setOperation(Operations.LOGIN_LAB);
        Laborant lab = (Laborant) request.getArgument();
        try {
            Laborant l = ServerController.getInstance().loginLaborant(lab);
            response.setResponse(l);
            response.setResponseType(ResponseType.SUCCESS);
            laborant = l;
            ServerController.getInstance().showLoggedUsers(l);
        } catch (Exception ex) {

            ex.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
        }
        return response;
    }

    public Lekar getLekar() {
        return lekar;
    }

    public Laborant getLaborant() {
        return laborant;
    }

    private Response findKartonPacijenta(Request request) {
        Response response = new Response();
        response.setOperation(Operations.GET_PACIJENT);
        try {
            KartonPacijenta k = ServerController.getInstance().findKartonPacijenta(request);
            System.out.println(k);
            List<Uput> up = new ArrayList<>();
            for (Uput u : up) {
                System.out.println(u);
            }
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(k);
        } catch (Exception ex) {
            response.setException(ex);
            response.setResponseType(ResponseType.ERROR);
            Logger.getLogger(ClientHandle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    private Response sendAllUputi() {
        Response response = new Response();
        response.setOperation(Operations.GET_ALL_UPUT);
        try {
            List<Uput> uputi = (List<Uput>) ServerController.getInstance().sendAllUputi();

            response.setResponse(uputi);
            response.setResponseType(ResponseType.SUCCESS);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(new Exception("Nema uputa"));
        }
        return response;
    }

    private Response insertRezultat(Request request) {
        Rezultat rez = (Rezultat) request.getArgument();
        Response response = new Response();
        response.setOperation(Operations.INSERT_REZULTAT);
        try {
            ServerController.getInstance().insertRezultat(rez);

            response.setResponseType(ResponseType.SUCCESS);
        } catch (Exception ex) {
            Logger.getLogger(ClientHandle.class.getName()).log(Level.SEVERE, null, ex);
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
        }
        return response;
    }

}
