package br.ufc.livraria.client;

import br.ufc.livraria.model.Livro;
import br.ufc.livraria.model.Venda;
import br.ufc.livraria.servidor.Message;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Proxy {
    //UDPClient udp = new UDPClient();
    TCPClient tcp = new TCPClient("localhost", 7896);
    DataInputStream in;
    DataOutputStream out;
    Gson gson = new Gson();
    Message m;

    public Proxy() throws IOException {

    }

    public String comprarLivro(Livro livro) throws IOException {
        m = new Message(0, "livraria", "comprarLivro", gson.toJson(livro));
        tcp.sendRequest(gson.toJson(m));
        return tcp.getResponse();
    }

    public String renovarEstoque(int id, int qtdCompra) throws IOException {
        String args = String.valueOf(id) + " " + String.valueOf(qtdCompra);
        m = new Message(0, "livraria", "renovarEstoque", args);
        tcp.sendRequest(gson.toJson(m));
        return tcp.getResponse();
    }

    public double consultarSaldo() throws IOException {
        m = new Message(0, "livraria", "consultarSaldo", "consultarSaldo");
        tcp.sendRequest(gson.toJson(m));
        return Double.parseDouble(tcp.getResponse());
    }

    public void finaliza() {
    }

    public ArrayList<Livro> consultarEstoque() throws IOException {
        m = new Message(0, "livraria", "consultarEstoque", "consultarEstoque");
        tcp.sendRequest(gson.toJson(m));
        return gson.fromJson(tcp.getResponse(), ArrayList.class);
    }

    public String venderLivro(int id, int qtdVenda) throws IOException {
        String args = String.valueOf(id) + " " + String.valueOf(qtdVenda);
        m = new Message(0, "livraria", "venderLivro", args);
        tcp.sendRequest(gson.toJson(m));
        return tcp.getResponse();
    }

    public ArrayList<Venda> consultarVendas() throws IOException {
        m = new Message(0, "livraria", "consultarVendas", "consultarVendas");
        tcp.sendRequest(gson.toJson(m));
        return gson.fromJson(tcp.getResponse(), ArrayList.class);
    }
}
