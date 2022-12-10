package br.ufc.livraria.client;

import br.ufc.livraria.model.Livro;
import br.ufc.livraria.model.Venda;
import br.ufc.livraria.servidor.Message;
import br.ufc.livraria.servidor.UDPServer;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.ArrayList;

public class Proxy {
    UDPClient udp = new UDPClient(UDPServer.PORT);

    public void tetse(String teste) throws IOException {
        udp.sendResponse(teste);
    }

    //    TCPClient tcp = new TCPClient("localhost", 7896);
    DataInputStream in;
    DataOutputStream out;
    Gson gson = new Gson();
    Message m;

    public Proxy() throws IOException {

    }

    public String comprarLivro(Livro livro) throws IOException {
        m = new Message(0, "livraria", "comprarLivro", gson.toJson(livro));
        udp.sendResponse(gson.toJson(m));
        return udp.getResponse();
    }

    public String renovarEstoque(int id, int qtdCompra) throws IOException {
        String args = String.valueOf(id) + " " + String.valueOf(qtdCompra);
        m = new Message(0, "livraria", "renovarEstoque", args);
        udp.sendResponse(gson.toJson(m));
        return udp.getResponse();
    }

    public double consultarSaldo() throws IOException {
        m = new Message(0, "livraria", "consultarSaldo", "consultarSaldo");
        udp.sendResponse(gson.toJson(m));
        return Double.parseDouble(udp.getResponse());
    }

    public void finaliza() {
    }

    public ArrayList<Livro> consultarEstoque() throws IOException {
        m = new Message(0, "livraria", "consultarEstoque", "consultarEstoque");
        udp.sendResponse(gson.toJson(m));
        Reader reader = new StringReader(udp.getResponse());
        JsonReader r = new JsonReader(reader);
        Gson gson = new Gson();
        ArrayList<Livro> livros = gson.fromJson(r, ArrayList.class);
        return livros;
    }

    public String venderLivro(int id, int qtdVenda) throws IOException {
        String args = String.valueOf(id) + " " + String.valueOf(qtdVenda);
        m = new Message(0, "livraria", "venderLivro", args);
        udp.sendResponse(gson.toJson(m));
        return udp.getResponse();
    }

    public ArrayList<Venda> consultarVendas() throws IOException {
        m = new Message(0, "livraria", "consultarVendas", "consultarVendas");
        udp.sendResponse(gson.toJson(m));
        Reader reader = new StringReader(udp.getResponse());
        JsonReader r = new JsonReader(reader);
        Gson gson = new Gson();
        ArrayList<Venda> vendas = gson.fromJson(r, ArrayList.class);
        return vendas;
    }
}
