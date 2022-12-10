package br.ufc.livraria.server;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;

public class LivrariaDespachante {

    public String invoke(String str) throws IOException {
        Reader reader = new StringReader(str);
        JsonReader r = new JsonReader(reader);
        Gson gson = new Gson();
        Message m = gson.fromJson(r, Message.class);
        String resposta = "";
        LivrariaEsqueleto e = new LivrariaEsqueleto();
        switch (m.methodId) {
            case "comprarLivro":
                resposta = e.comprarLivro(m.arguments);
                break;
            case "renovarEstoque":
                resposta = e.renovarEstoque(m.arguments);
                break;
            case "consultarEstoque":
                resposta = e.consultarEstoque();
                break;
            case "consultarVendas":
                resposta = e.consultarVendas();
                break;
            case "consultarSaldo":
                resposta = e.consultarSaldo();
                break;
            case "venderLivro":
                resposta = e.venderLivro(m.arguments);
                break;
            default:
                System.out.println("Erro");
                break;
        }
        return resposta;
    }
}
