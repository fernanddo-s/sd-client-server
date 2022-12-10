package br.ufc.livraria.server;

import br.ufc.livraria.model.Livro;
import br.ufc.livraria.model.Transacao;
import br.ufc.livraria.model.Venda;
import com.google.gson.Gson;

import java.util.ArrayList;

public class LivrariaEsqueleto {
    Livraria livraria;

    public LivrariaEsqueleto() {
        livraria = Livraria.getInstance();
    }

    //deve mostrar a lista de livros em estoque
    public String consultarEstoque() {
        ArrayList<Livro> livros = livraria.consultarEstoque();
        Gson gson = new Gson();
        return gson.toJson(livros);
    }

    public String consultarSaldo() {
        return Double.toString(livraria.getSaldo());
    }

    public String comprarLivro(String args) {
        Gson gson = new Gson();
        Livro livro = gson.fromJson(args, Livro.class);
        return livraria.comprarLivro(livro);
    }

    public String renovarEstoque(String args) {
//        String[] str = args.split(" ");
        Gson gson = new Gson();
        Transacao t = gson.fromJson(args, Transacao.class);
        return livraria.renovarEstoque(t);
    }

    public String venderLivro(String args) {
//        String[] str = args.split(" ");
        Gson gson = new Gson();
        Transacao t = gson.fromJson(args, Transacao.class);
        return livraria.venderLivro(t);
    }

    public String consultarVendas() {
        ArrayList<Venda> vendas = livraria.consultarVendas();
        Gson gson = new Gson();
        return gson.toJson(vendas);
    }
}
