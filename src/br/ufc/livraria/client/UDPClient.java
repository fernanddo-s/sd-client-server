package br.ufc.livraria.client;

import java.io.IOException;
import java.net.*;

public class UDPClient {
    public static int PORT = 8976;
    DatagramSocket clienteSocket;
    DatagramPacket enviaPacote;
    byte[] enviaDados;
    byte[] recebeDados;

    UDPClient(int port) throws SocketException {
        this.clienteSocket = new DatagramSocket(port);
    }

    public void sendResponse(String args) throws IOException {
        enviaDados = new byte[1024];
        enviaDados = args.getBytes();

        enviaPacote = new DatagramPacket(enviaDados, args.getBytes().length, InetAddress.getByName("localhost"), PORT);

        clienteSocket.send(enviaPacote);
        enviaDados = new byte[1024];
    }

    public String getResponse() throws IOException {
        recebeDados = new byte[1024];
        DatagramPacket recebePacote = new DatagramPacket(recebeDados, recebeDados.length);

        clienteSocket.receive(recebePacote);

        String msgServer = new String(recebePacote.getData(), 0, recebeDados.length);
        recebeDados = new byte[1024];
        return msgServer;
    }
}
