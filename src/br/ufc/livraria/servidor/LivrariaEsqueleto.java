package br.ufc.livraria.servidor;

import br.ufc.livraria.model.Livro;
import br.ufc.livraria.model.Venda;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

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

    public String consultarSaldo(){
        return Double.toString(livraria.getSaldo());
    }

    public String comprarLivro(Livro livro){
        return  livraria.comprarLivro(livro);
    }

    public String renovarEstoque(int id, int qtdCompra){
        return livraria.renovarEstoque(id,qtdCompra);
    }

    public String venderLivro(int id, int qtdVenda) {
        return livraria.venderLivro(id, qtdVenda);
    }

    public String consultarVendas() {
        ArrayList<Venda> vendas = livraria.consultarVendas();
        Gson gson = new Gson();
        return gson.toJson(vendas);
    }


//    public byte[] remove(ByteString args) {
//        // (1) Desempacota argumento de entrada
//        // (2) chama o metodo do servente
//        // (3) empacota resposta do método servente e retorna
//    }
}
