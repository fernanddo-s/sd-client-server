package br.ufc.livraria.servidor;

import br.ufc.livraria.model.Livro;
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
        String[] str = args.split(" ");
        return livraria.renovarEstoque(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
    }

    public String venderLivro(String args) {
        String[] str = args.split(" ");
        return livraria.venderLivro(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
    }

    public String consultarVendas() {
        ArrayList<Venda> vendas = livraria.consultarVendas();
        Gson gson = new Gson();
        return gson.toJson(vendas);
    }
}
