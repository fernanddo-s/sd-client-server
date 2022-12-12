package br.ufc.livraria.server;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class LivrariaDespachante {

    public String invoke(String str) throws IOException {
        Reader reader = new StringReader(str);
        JsonReader r = new JsonReader(reader);
        Gson gson = new Gson();
        Message m = gson.fromJson(r, Message.class);
        String resposta = "";
        LivrariaEsqueleto e = new LivrariaEsqueleto();
        switch (m.getMethodId()) {
            case "comprarLivro":
                resposta = e.comprarLivro(m.getArguments());
                break;
            case "renovarEstoque":
                resposta = e.renovarEstoque(m.getArguments());
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
                resposta = e.venderLivro(m.getArguments());
                break;
            default:
                System.out.println("Erro");
                break;
        }
        return resposta;
    }
}
