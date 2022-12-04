package br.ufc.livraria.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPClient {
    Socket s = null;
    DataInputStream in;
    DataOutputStream out;

    public TCPClient(String ipServer, int serverPort) throws IOException {
        this.s = new Socket(ipServer, serverPort);
        this.in = new DataInputStream(s.getInputStream());
        this.out = new DataOutputStream(s.getOutputStream());
    }

    public void sendRequest(String str) throws IOException {
        out.writeUTF(str);
    }

    public String getResponse() throws IOException {
        String data = in.readUTF();
        return data;
    }

    public void close() throws IOException {
        try {
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}